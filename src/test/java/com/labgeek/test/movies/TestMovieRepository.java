package com.labgeek.test.movies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.labgeek.moviesapi.config.DataBaseConnection;
import com.labgeek.moviesapi.model.Movie;
import com.labgeek.moviesapi.repository.MovieRepository;

class TestMovieRepository {

	private DataBaseConnection mockDb;
	private Connection mockConnection;
	private PreparedStatement mockStatement;
	private ResultSet mockResultSet;

	private MovieRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		mockDb = mock(DataBaseConnection.class);
		mockConnection = mock(Connection.class);
		mockStatement = mock(PreparedStatement.class);
		mockResultSet = mock(ResultSet.class);

		when(mockDb.getConnection()).thenReturn(mockConnection);

		repository = new MovieRepository(mockDb);
	}

	@Test
	void testFindAllReturnsMovies() throws Exception {
		when(mockConnection.prepareStatement("SELECT * FROM movies")).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		// Mock ResultSet
		when(mockResultSet.next()).thenReturn(true, false); // one row only
		when(mockResultSet.getInt("id")).thenReturn(1);
		when(mockResultSet.getString("title")).thenReturn("Inception");
		when(mockResultSet.getString("director")).thenReturn("Christopher Nolan");
		when(mockResultSet.getString("genre")).thenReturn("Sci-Fi");

		List<Movie> movies = repository.findAll();

		assertNotNull(movies);
		assertEquals(1, movies.size());
		assertEquals("Inception", movies.get(0).getTitle());
		assertEquals("Christopher Nolan", movies.get(0).getDirector());
		assertEquals("Sci-Fi", movies.get(0).getGenre());
	}
	
	@Test
	void testFindByIdReturnsMovies() throws Exception {
		when(mockConnection.prepareStatement("SELECT * FROM movies where id=?")).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		// Mock ResultSet
		when(mockResultSet.next()).thenReturn(true); // one row only
		when(mockResultSet.getInt("id")).thenReturn(1);
		when(mockResultSet.getString("title")).thenReturn("Inception");
		when(mockResultSet.getString("director")).thenReturn("Christopher Nolan");
		when(mockResultSet.getString("genre")).thenReturn("Sci-Fi");

		Movie movie = repository.findById(1);

		assertNotNull(movie);
		assertEquals("Inception", movie.getTitle());
		assertEquals("Christopher Nolan", movie.getDirector());
		assertEquals("Sci-Fi", movie.getGenre());
	}

	@Test
	void testFindAllEmptyResult() throws Exception {
		when(mockConnection.prepareStatement("SELECT * FROM movies")).thenReturn(mockStatement);
		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(false);

		List<Movie> movies = repository.findAll();

		assertNotNull(movies);
		assertTrue(movies.isEmpty());
	}

	@Test
	void testFindAllThrowsException() throws Exception {

		when(mockConnection.prepareStatement("SELECT * FROM movies")).thenThrow(new RuntimeException("DB error"));

		RuntimeException ex = assertThrows(RuntimeException.class, () -> repository.findAll());
		assertTrue(ex.getMessage().contains("DB error"));
	}

	@Test
	void testCreateMovie() throws SQLException {
		when(mockConnection.prepareStatement("Insert into movies(title,director,year,genre) values (?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS)).thenReturn(mockStatement);
		when(mockStatement.executeUpdate()).thenReturn(1);

		// --- Step 4: Stub getGeneratedKeys to return mockResultSet ---
		when(mockStatement.getGeneratedKeys()).thenReturn(mockResultSet);

		// --- Step 5: Stub resultSet.next() and getInt(1) ---
		when(mockResultSet.next()).thenReturn(true); // first call returns true
		when(mockResultSet.getInt(1)).thenReturn(42); // generated ID

		MovieRepository repo = new MovieRepository(mockDb); // adapt your constructor
		Movie movie = new Movie();
		movie.setTitle("Inception");
		movie.setDirector("Nolan");
		movie.setYear("2010");
		movie.setGenre("Sci-Fi");

		Movie saved = repo.create(movie);

		assertNotNull(saved);
		assertEquals(42, saved.getId());
		assertEquals("Inception", saved.getTitle());

	}

	@Test
	void testUpdateMovie() throws SQLException {
		when(mockConnection.prepareStatement("UPDATE movies SET title=?,director=?,year=?,genre=? WHERE id=?"))
				.thenReturn(mockStatement);

		when(mockStatement.executeUpdate()).thenReturn(1);

		when(mockConnection.prepareStatement("SELECT * FROM movies where id=?")).thenReturn(mockStatement);

		when(mockStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(true);
		when(mockResultSet.getInt("id")).thenReturn(1);
		when(mockResultSet.getString("title")).thenReturn("Inception");

		MovieRepository repo = new MovieRepository(mockDb);
		Movie movie = new Movie();
		movie.setId(mockResultSet.getInt(1));
		movie.setTitle(mockResultSet.getString("title"));

		Movie updatedMovie = repo.update(movie, 1);

		assertNotNull(updatedMovie);
		assertEquals(1, updatedMovie.getId());
		assertEquals("Inception", updatedMovie.getTitle());

	}

	@Test
	void testDeleteMovie() throws SQLException {
		when(mockConnection.prepareStatement("DELETE FROM movies WHERE id=?")).thenReturn(mockStatement);

		when(mockStatement.executeUpdate()).thenReturn(1);

		MovieRepository repo = new MovieRepository(mockDb);

		boolean deletedMovie = repo.delete(1);

		assertEquals(true, deletedMovie);

	}
}
