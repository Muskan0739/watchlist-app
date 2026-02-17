package com.example.muskan.WatchList.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.muskan.WatchList.App.entity.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	 List<Movie> findByUserEmail(String userEmail); 

}
