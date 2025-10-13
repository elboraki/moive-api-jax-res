package com.labgeek.test.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.labgeek.moviesapi.model.Movie;

class TestMoiveModel {
	static Movie movie;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		movie=new Movie();
		movie.setTitle("Titanic");
		movie.setId(101);
		movie.setDirector("Leo");
		movie.setGenre("Drama");
	}

	@Test
	void testTitleMovie() {
	 assertEquals("Titanic", movie.getTitle());
	 assertNotNull(movie);
	}

	@Test
	void testDramaMovie() {
	 assertEquals("Drama", movie.getGenre());
	 assertNotNull(movie);
	}

}
