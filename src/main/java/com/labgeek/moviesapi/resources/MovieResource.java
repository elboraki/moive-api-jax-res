
package com.labgeek.moviesapi.resources;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.labgeek.moviesapi.config.DataBaseConnection;
import com.labgeek.moviesapi.repository.MovieRepository;
import com.labgeek.moviesapi.services.MovieService;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    @GET
    public Response getAllMovies() {
		try {
			DataBaseConnection db = DataBaseConnection.getInstance();
			MovieRepository movieRepo=new MovieRepository(db);
	    	MovieService service=new MovieService(movieRepo);
	        return  Response.ok(service.getAllMovies()).build();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return Response.serverError().entity("Database failed").build();
		}
    	
    }
}

