package com.labgeek.moviesapi.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.labgeek.moviesapi.config.DataBaseConnection;
import com.labgeek.moviesapi.model.Movie;
import com.labgeek.moviesapi.repository.MovieRepository;
import com.labgeek.moviesapi.services.MovieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Movies", description = "API for managing movies")
public class MovieResource {
    
    private MovieService movieService;
    
    public MovieResource() {
        this.movieService = null;
    }

    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    @GET
    @Operation(
        summary = "Get all movies",
        description = "Retrieve a list of all movies",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved movies"),
            @ApiResponse(responseCode = "500", description = "Database error")
        }
    )
    public Response getAllMovies() {
        try {
            DataBaseConnection db = DataBaseConnection.getInstance();
            MovieRepository movieRepo = new MovieRepository(db);
            MovieService service = new MovieService(movieRepo);
            List<Movie> movies = service.getAllMovies();
            return Response.ok(movies).build();
        } catch (SQLException e) {
            return Response.serverError().entity("Database failed").build();
        }
    }
    
    @POST
    @Operation(
        summary = "Create a new movie",
        description = "Add a new movie to the database",
        responses = {
            @ApiResponse(responseCode = "201", description = "Movie created successfully"),
            @ApiResponse(responseCode = "500", description = "Database error")
        }
    )
    public Response createMovies(Movie movie) {
        try {
            DataBaseConnection db = DataBaseConnection.getInstance();
            MovieRepository movieRepo = new MovieRepository(db);
            MovieService service = new MovieService(movieRepo);
            Movie movieCreated = service.createMovie(movie);
            return Response.status(Response.Status.CREATED).entity(movieCreated).build();
        } catch (SQLException e) {
            return Response.serverError().entity("Database failed").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Operation(
        summary = "Update an existing movie",
        description = "Update movie details by ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully"),
            @ApiResponse(responseCode = "500", description = "Database error")
        }
    )
    public Response updateMovies(@PathParam("id") int id, Movie movie) {
        try {
            DataBaseConnection db = DataBaseConnection.getInstance();
            MovieRepository movieRepo = new MovieRepository(db);
            MovieService service = new MovieService(movieRepo);
            Movie movieUpdated = service.updateMovie(movie, id);
            return Response.ok(movieUpdated).build();
        } catch (SQLException e) {
            return Response.serverError().entity("Database failed").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Operation(
        summary = "Delete a movie",
        description = "Delete a movie by ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Movie deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Movie not found"),
            @ApiResponse(responseCode = "500", description = "Database error")
        }
    )
    public Response deleteMovies(@PathParam("id") int id) {
        try {
            DataBaseConnection db = DataBaseConnection.getInstance();
            MovieRepository movieRepo = new MovieRepository(db);
            this.movieService = new MovieService(movieRepo);
            boolean deleted = this.movieService.deleteMovie(id);
            if (deleted) {
                return Response.ok("{\"Message\":\"Success! the movie has been deleted\"}").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{\"Message\":\"Movie not found\"}")
                               .build();
            }
        } catch (SQLException e) {
            return Response.serverError().entity("Database failed").build();
        }
    }
}
