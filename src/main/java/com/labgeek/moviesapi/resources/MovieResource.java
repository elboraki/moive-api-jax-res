package com.labgeek.moviesapi.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.labgeek.moviesapi.config.DataBaseConnection;
import com.labgeek.moviesapi.model.Movie;
import com.labgeek.moviesapi.repository.MovieRepository;
import com.labgeek.moviesapi.services.MovieService;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class MovieResource {

    // ✅ Add this no-arg constructor
    public MovieResource() {
        // Jersey needs this to create an instance
    }

    @GET
    public Response getAllMovies() {
        try {
            DataBaseConnection db = DataBaseConnection.getInstance();
            MovieRepository movieRepo = new MovieRepository(db);
            MovieService service = new MovieService(movieRepo);
            return Response.ok(service.getAllMovies()).build();
        } catch (SQLException e) {
            return Response.serverError().entity("Database failed").build();
        }
    }
    
    @POST
    public Response CreateMovies(Movie movie) {
        try {
            DataBaseConnection db = DataBaseConnection.getInstance();
            MovieRepository movieRepo = new MovieRepository(db);
            MovieService service = new MovieService(movieRepo);
          
            Movie movieCreated = service.createMovie(movie);
            return Response.status(Response.Status.CREATED)
                    .entity(movieCreated)
                    .build();

        } catch (SQLException e) {
            return Response.serverError().entity("Database failed").build();
        }
    }
}
