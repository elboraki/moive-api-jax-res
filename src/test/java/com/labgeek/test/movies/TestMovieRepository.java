package com.labgeek.test.movies;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
        when(mockConnection.prepareStatement("SELECT * FROM movies")).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        repository = new MovieRepository(mockDb);
    }

    @Test
    void testFindAllReturnsMovies() throws Exception {
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
    void testFindAllEmptyResult() throws Exception {
        when(mockResultSet.next()).thenReturn(false);

        List<Movie> movies = repository.findAll();

        assertNotNull(movies);
        assertTrue(movies.isEmpty());
    }

    @Test
    void testFindAllThrowsException() throws Exception {
        when(mockConnection.prepareStatement("SELECT * FROM movies"))
                .thenThrow(new RuntimeException("DB error"));

        RuntimeException ex = assertThrows(RuntimeException.class, () -> repository.findAll());
        assertTrue(ex.getMessage().contains("DB error"));
    }
}
