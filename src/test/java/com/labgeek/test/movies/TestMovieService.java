package com.labgeek.test.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.labgeek.moviesapi.model.Movie;
import com.labgeek.moviesapi.repository.MovieRepository;
import com.labgeek.moviesapi.services.MovieService;


class TestMovieService {
	private MovieRepository movieRepositoryMock;
	private MovieService service;
	private Movie m1;
	private Movie m2;

	@BeforeEach
	void setUp() throws Exception {
		movieRepositoryMock=mock(MovieRepository.class);
		service=new MovieService(movieRepositoryMock);
		m1=new Movie();
		m1.setId(1);
		m1.setTitle("Titanic");
		m1.setGenre("Drama");

		m2=new Movie();
		m2.setId(1);
		m2.setTitle("Matrix");
		m2.setGenre("Action");
	}

	@Test
	void testGetListOfMoviesandReturnList() {
		when(movieRepositoryMock.findAll()).thenReturn(Arrays.asList(m1,m2));
		List<Movie> movieList=service.getAllMovies();
		assertEquals(2,movieList.size());
		assertEquals("Matrix",movieList.get(1).getTitle());

	}

	 @Test
	    void testGetAllMoviesReturnsEmptyList() {
	        when(movieRepositoryMock.findAll()).thenReturn(Collections.emptyList());

	        List<Movie> movies = service.getAllMovies();

	        assertNotNull(movies);
	        assertTrue(movies.isEmpty());
	    }

	    @Test
	    void testGetAllMoviesThrowsException() {
	        when(movieRepositoryMock.findAll()).thenThrow(new RuntimeException("DB error"));

	        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.getAllMovies());
	        assertEquals("DB error", ex.getMessage());
	    }
	    
	    @Test
	    void testCreateMovie() {
	    	 Movie movie = new Movie();
	         movie.setTitle("Inception");
	         movie.setDirector("Nolan");
	         movie.setYear("2010");
	         movie.setGenre("Sci-Fi");
	        when(movieRepositoryMock.create(movie)).thenReturn(movie);
	        
	        assertEquals("2010", movie.getYear());

	        
	    }
	    
	    @Test
	    void testUpdateMovie() {
	    	 Movie movie = new Movie();
	    	 movie.setId(1);
	         movie.setTitle("Inception");
	         movie.setDirector("Nolan");
	         movie.setYear("2010");
	         movie.setGenre("Sci-Fi");
	        when(movieRepositoryMock.update(movie,1)).thenReturn(movie);
	        
	        assertEquals("2010", movie.getYear());

	        
	    }

}
