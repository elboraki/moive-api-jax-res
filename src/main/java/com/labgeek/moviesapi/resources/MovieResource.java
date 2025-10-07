
package com.labgeek.moviesapi.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    @GET
    @Path("/all")
    public String getAllMovies() {
        return "[{\"id\":1,\"title\":\"The Matrix\",\"director\":\"Wachowski\"}," +
               "{\"id\":2,\"title\":\"Inception\",\"director\":\"Christopher Nolan\"}]";
    }
}

