package com.duolingo.client.rmi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Language implements Serializable {

	private long language_id;
	private String language_name;

	private List<Course> courses;
	
	// Constructors
	public Language() {
		this.courses = new ArrayList<Course>();
	}
	
	public Language(String language_name) {
		super();
		this.language_name = language_name;
		this.courses = new ArrayList<Course>();
	}
	
	public Language(long language_id, String language_name) {
		super();
		this.language_id = language_id;
		this.language_name = language_name;
		this.courses = new ArrayList<Course>();
	}

	// Getters && Setters:
	public long getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(long language_id) {
		this.language_id = language_id;
	}

	public String getLanguage_name() {
		return language_name;
	}

	public void setLanguage_name(String language_name) {
		this.language_name = language_name;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course) {
		this.courses.add(course);
	}
	
	

}
