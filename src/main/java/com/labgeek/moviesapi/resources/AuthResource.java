package com.labgeek.moviesapi.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import com.labgeek.moviesapi.config.DataBaseConnection;
import com.labgeek.moviesapi.model.UserCredentials;
import com.labgeek.moviesapi.repository.UserCredentialsRepository;
import com.labgeek.moviesapi.services.UserCredentialsService;
import com.labgeek.moviesapi.util.JwtUtil;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthResource {

	@POST
	@Path("/login")
	public Response login(UserCredentials cridentials) throws SQLException {
		DataBaseConnection db = DataBaseConnection.getInstance();
		UserCredentialsRepository rp = new UserCredentialsRepository(db);
		UserCredentialsService service = new UserCredentialsService(rp);

		UserCredentials userCredentials = service.authenticateUser(cridentials.getUsername(),
				cridentials.getPassword());
		if (userCredentials != null) {
            String token = JwtUtil.generateToken(userCredentials.getUsername());
            String Username=JwtUtil.extractUserName(token);
			return Response.status(Response.Status.ACCEPTED).entity("{\"token\":\""+token.toString()+"\",\"username\":\""+Username+"\"}")
					.build();
		}else {
			return Response.status(Response.Status.NOT_FOUND).entity("{\"message\":\"authentification has been failed \"}")
					.build();
		}

	}

}
