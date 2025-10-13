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
		this.db=db;
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
		// Full texts	id	title	director	year	genre
		String query="Insert into movies(title,director,year,genre) values (?,?,?,?)";
		try {
			PreparedStatement ps=this.db.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, movie.getTitle());
			ps.setString(2, movie.getDirector());
			ps.setString(3, movie.getYear());
			ps.setString(4, movie.getGenre());

			int  row=ps.executeUpdate();
			
	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                movie.setId(generatedKeys.getInt(1)); 
	            } else {
	                throw new SQLException("Creating movie failed, no ID obtained.");
	            }
	        }

	        return movie; 
		}catch(SQLException e) {
			throw new RuntimeException("Creating movie has been failed",e);
		}
	}
}
