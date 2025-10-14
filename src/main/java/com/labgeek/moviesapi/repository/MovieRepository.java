package com.labgeek.moviesapi.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.labgeek.moviesapi.config.DataBaseConnection;
import com.labgeek.moviesapi.model.Movie;

public class MovieRepository {
	Movie movie = null;
	private final DataBaseConnection db;

	public MovieRepository(DataBaseConnection db) {
		this.db = db;
	}

	public List<Movie> findAll() {
		String query = "SELECT * FROM movies";
		List<Movie> movieList = new ArrayList<>();
		try {
			PreparedStatement ps = this.db.getConnection().prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				movie = new Movie();
				movie.setId(rs.getInt("id"));
				movie.setTitle(rs.getString("title"));
				movie.setDirector(rs.getString("director"));
				movie.setGenre(rs.getString("genre"));
				movieList.add(movie);
			}
			return movieList;

		} catch (SQLException e) {
			throw new RuntimeException("Error fecthing data", e);
		}
	}

	public Movie create(Movie movie) {
		// Full texts id title director year genre
		String query = "Insert into movies(title,director,year,genre) values (?,?,?,?)";
		try {
			PreparedStatement ps = this.db.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, movie.getTitle());
			ps.setString(2, movie.getDirector());
			ps.setString(3, movie.getYear());
			ps.setString(4, movie.getGenre());

			int row = ps.executeUpdate();

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					movie.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating movie failed, no ID obtained.");
				}
			}

			return movie;
		} catch (SQLException e) {
			throw new RuntimeException("Creating movie has been failed", e);
		}
	}

	public Movie update(Movie movie, int id) {
		// Full texts id title director year genre
		String query = "UPDATE movies SET title=?,director=?,year=?,genre=? WHERE id=?";
		try {
			PreparedStatement ps = this.db.getConnection().prepareStatement(query);
			ps.setString(1, movie.getTitle());
			ps.setString(2, movie.getDirector());
			ps.setString(3, movie.getYear());
			ps.setString(4, movie.getGenre());
			ps.setInt(5, id);

			int row = ps.executeUpdate();
			if (row == 0) {
				return null;
			}

			query = "SELECT * FROM movies where id=?";
			ps = this.db.getConnection().prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Movie updatedMovie = new Movie();
				updatedMovie.setId(rs.getInt("id"));
				updatedMovie.setTitle(rs.getString("title"));
				updatedMovie.setDirector(rs.getString("director"));
				updatedMovie.setYear(rs.getString("year"));
				updatedMovie.setGenre(rs.getString("genre"));

				return updatedMovie;
			}

			return null;
		} catch (SQLException e) {
			throw new RuntimeException("Updating movie has been failed", e);
		}
	}
	
	
	public boolean delete(int id) {
		String query = "DELETE FROM movies WHERE id=?";
		try {
			PreparedStatement ps = this.db.getConnection().prepareStatement(query);
			ps.setInt(1, id);

			int row = ps.executeUpdate();
			if (row >0) {
				return true;
			}


		} catch (SQLException e) {
			throw new RuntimeException("deleting movie has been failed", e);
		}
		return false;
	}
}
