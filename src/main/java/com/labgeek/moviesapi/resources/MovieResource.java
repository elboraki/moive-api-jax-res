package com.labgeek.moviesapi.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
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

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class MovieResource {


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
    public Response createMovies(Movie movie) {
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
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMovies(@PathParam("id") int id,Movie movie) {
        try {
            DataBaseConnection db = DataBaseConnection.getInstance();
            MovieRepository movieRepo = new MovieRepository(db);
            MovieService service = new MovieService(movieRepo);
          
            Movie movieUpdate = service.updateMovie(movie,id);
            return Response.status(Response.Status.OK)
                    .entity(movieUpdate)
                    .build();

        } catch (SQLException e) {
            return Response.serverError().entity("Database failed").build();
        }
    }


}
