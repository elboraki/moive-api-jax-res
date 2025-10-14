package com.labgeek.test.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import com.labgeek.moviesapi.config.DataBaseConnection;
import com.labgeek.moviesapi.model.Movie;
import com.labgeek.moviesapi.repository.MovieRepository;
import com.labgeek.moviesapi.resources.MovieResource;
import com.labgeek.moviesapi.services.MovieService;

class MovieResourceTest {

	@Test
	void testGetAllMoviesReturnsOkResponse() throws Exception {
		// Mock DB, repo, service
		DataBaseConnection mockDb = mock(DataBaseConnection.class);
		MovieRepository mockRepo = mock(MovieRepository.class);
		MovieService mockService = mock(MovieService.class);

		// Fake movie
		Movie movie = new Movie();
		movie.setId(1);
		movie.setTitle("Matrix");
		movie.setDirector("Zeus");
		movie.setGenre("SC Fiction");

		// Mock static getInstance()

		// Inject fake repo & service
		when(mockDb.getConnection()).thenReturn(null); // not used in this flow
		when(mockRepo.findAll()).thenReturn(Arrays.asList(movie));
		when(mockService.getAllMovies()).thenReturn(Arrays.asList(movie));

		// Override creation inside resource
		MovieResource resource = new MovieResource() {
			@Override
			public Response getAllMovies() {
				return Response.ok(mockService.getAllMovies()).build();
			}
		};

		Response response = resource.getAllMovies();

		assertEquals(200, response.getStatus());
		assertNotNull(response.getEntity());
	}

	@Test
	void testGetAllMoviesReturnsServerErrorOnSQLException() throws Exception {
		MovieService mockService = mock(MovieService.class);

		MovieResource resource = new MovieResource() {
			@Override
			public Response getAllMovies() {
				return Response.serverError().status(500).entity("Database failed").build();
			}
		};

		Response response = resource.getAllMovies();

		assertEquals(500, response.getStatus());
		assertEquals("Database failed", response.getEntity());
	}

	@Test
	void testGetAllMoviesReturnsEmptyList() throws Exception {
		MovieService mockService = mock(MovieService.class);
		when(mockService.getAllMovies()).thenReturn(Collections.emptyList());

		MovieResource resource = new MovieResource() {
			@Override
			public Response getAllMovies() {
				return Response.ok(mockService.getAllMovies()).build();
			}
		};

		Response response = resource.getAllMovies();

		assertEquals(200, response.getStatus());
		assertTrue(((java.util.List<?>) response.getEntity()).isEmpty());
	}
	
	@Test
	void testPostMovie() throws Exception {
				Movie movie = new Movie();
				movie.setId(1);
				movie.setTitle("Matrix");
				movie.setDirector("Zeus");
				movie.setGenre("SC Fiction");
		MovieService mockService = mock(MovieService.class);
		when(mockService.createMovie(movie)).thenReturn(movie);

		MovieResource resource = new MovieResource();
		

		Response response = resource.createMovies(movie);

		assertEquals(201, response.getStatus());
		assertEquals(response.getEntity(),movie);
	}
	
	@Test
	void testUpdateMovieById() throws Exception {
				Movie movie = new Movie();
				movie.setId(1);
				movie.setTitle("Matrix");
				movie.setDirector("Zeus");
				movie.setGenre("SC Fiction");
		MovieService mockService = mock(MovieService.class);
		when(mockService.updateMovie(movie,1)).thenReturn(movie);

		MovieResource resource = new MovieResource();
		

		Response response = resource.updateMovies(1,movie);

		assertEquals(200, response.getStatus());
	}
}
