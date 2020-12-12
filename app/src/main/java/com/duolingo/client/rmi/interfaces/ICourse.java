package com.duolingo.client.rmi.interfaces;

import java.util.List;

import com.duolingo.client.rmi.models.Course;

public interface ICourse {

	public List<Course> getAllCourses();
	
	public Course getCourseById(long course_id);
	
	public List<Course> getCoursesByOriginLanguage(String language_name);
	
	public List<Course> getCoursesByDestinationLanguage(String language_name);
	
	public Course getCourseByLanguage(String origin_language, String destination_language);
	
	public boolean deleteCourse(Course course);
	
	public Course insertCourse(Course course);
	
	public Course updateCourse(Course course);
}
