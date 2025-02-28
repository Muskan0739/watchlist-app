package com.example.muskan.WatchList.App.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.muskan.WatchList.App.entity.Movie;
import com.example.muskan.WatchList.App.repository.MovieRepository;

@Service
public class DatabaseService {
	@Autowired
	MovieRepository mr;

	@Autowired
	RatingService ratingservice;
	
	public void createMovie(Movie movie) {
		
		String rating= ratingservice.getMovieRating(movie.getTitle());
		if(rating!=null) {
			movie.setRating(Float.parseFloat(rating));
		}
		mr.save(movie);
	}
	
	public List<Movie> getAllMoview(){
		
		return mr.findAll();//returns list of all the movies in the db
	}
	
	public Movie getMoviebyId(Integer id) {
		return mr.findById(id).get();
	}

	public void update(Movie movie, Integer id) {
		// TODO Auto-generated method stub
		Movie newMovie=getMoviebyId(id);
		
		newMovie.setTitle(movie.getTitle());
		newMovie.setRating(movie.getRating());
		newMovie.setPriority(movie.getPriority());
		newMovie.setComment(movie.getComment());
		
		mr.save(newMovie);
	}
}
