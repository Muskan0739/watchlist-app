package com.example.muskan.WatchList.App.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {

	String apiURL= "https://www.omdbapi.com/?i=tt3896198&apikey=d3b22ff7&t=";
	
	public String getMovieRating(String title) {
		try {
			//try to fetch the rating by calling the omdb API
			RestTemplate template=new RestTemplate();
			//we will call apiURL + movie title
			
			ResponseEntity<ObjectNode> response= template.getForEntity(apiURL + title, ObjectNode.class);
			ObjectNode jsonObj= response.getBody();
			
			return jsonObj.path("imdbRating").asText();
		}
		catch(Exception e){
			//else user entered rating will be entered
			System.out.println("Either movie name is not available or API is down"+ e.getMessage());
			return null;
		}
	}
}
