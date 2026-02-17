package com.example.muskan.WatchList.App.entity;

import com.example.muskan.WatchList.App.entity.validations.Priority;
import com.example.muskan.WatchList.App.entity.validations.Rating;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;
	@NotBlank(message="Please enter the title")
	public String title;
	
	@Rating
	public float rating;
	
	@Priority
	public String priority;
	
	@Size(max=50, message="Maximum 50 characters")
	public String comment;
	
	private String userEmail; 

    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	

}