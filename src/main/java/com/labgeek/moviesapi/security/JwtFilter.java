package com.labgeek.moviesapi.security;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.labgeek.moviesapi.util.JwtUtil;

@Provider
@Priority(Priorities.AUTHENTICATION)
@Secured
public class JwtFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeader = requestContext.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\":\"Missing or invalid Authorization header\"}")
                        .build()
            );
            return;
        }

        String token = authHeader.substring("Bearer".length()).trim();

        if (!JwtUtil.validateToken(token)) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\":\"Invalid or expired token\"}")
                        .build()
            );
        }
    }
}
