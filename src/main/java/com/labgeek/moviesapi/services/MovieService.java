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
}
