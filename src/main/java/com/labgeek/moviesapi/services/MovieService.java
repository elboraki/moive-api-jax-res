package com.labgeek.moviesapi.services;

import java.util.List;

import com.labgeek.moviesapi.model.Movie;
import com.labgeek.moviesapi.repository.MovieRepository;

public class MovieService {
	private MovieRepository movieRepository;

	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	public List<Movie> getAllMovies(){
		return this.movieRepository.findAll();
	}
	
	public Movie createMovie(Movie movie){

		return this.movieRepository.create(movie);
	
	}
	public Movie updateMovie(Movie movie,int id){

		return this.movieRepository.update(movie, id);
	
	}
	
	public boolean deleteMovie(int id){

		return this.movieRepository.delete(id);
	
	}
}
