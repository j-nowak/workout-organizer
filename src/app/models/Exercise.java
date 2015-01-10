package models;

import java.util.Set;

public class Exercise {
	
	private String name;
	private String description;
	private String movieUri;
	private Set<MuscleGroup> targetMuscles;
	
	private double rating;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getMovieUri() {
		return movieUri;
	}
	
	public void setMovieUri(String movieUri) {
		this.movieUri = movieUri;
	}

	public Set<MuscleGroup> getTargetMuscles() {
		return targetMuscles;
	}

	public void setTargetMuscles(Set<MuscleGroup> targetMuscles) {
		this.targetMuscles = targetMuscles;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
}
