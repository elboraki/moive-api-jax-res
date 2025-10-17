package com.labgeek.moviesapi.config;

import org.glassfish.jersey.server.ResourceConfig;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
@OpenAPIDefinition(
    info = @Info(
        title = "Movies API",
        version = "1.0",
        description = "API for managing movies"
    )
)
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        packages("com.labgeek.moviesapi.resources");
        register(OpenApiResource.class);
    }
}
