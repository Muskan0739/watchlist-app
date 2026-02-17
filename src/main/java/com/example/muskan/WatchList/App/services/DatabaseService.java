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

    // Takes email now so we can associate movie with user
    public void createMovie(Movie movie, String email) {
        String rating = ratingservice.getMovieRating(movie.getTitle());
        if (rating != null) {
            movie.setRating(Float.parseFloat(rating));
        }
        movie.setUserEmail(email);  // link movie to user
        mr.save(movie);
    }

    // Only fetch movies belonging to this user
    public List<Movie> getAllMovies(String email) {
        return mr.findByUserEmail(email);
    }

    public Movie getMoviebyId(Integer id) {
        return mr.findById(id).get();
    }

    public void update(Movie movie, Integer id) {
        Movie newMovie = getMoviebyId(id);
        newMovie.setTitle(movie.getTitle());
        newMovie.setRating(movie.getRating());
        newMovie.setPriority(movie.getPriority());
        newMovie.setComment(movie.getComment());
        mr.save(newMovie);
    }
}