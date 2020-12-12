package com.duolingo.client.rmi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Exercise implements Serializable {

	private long exercise_id;
	private String exercise_name;
	private int exercise_exp;
	private String content;
	private boolean isFinished;

    private Level level;
	private List<User> users;
	private List<ExerciseType> types;
	
	// Constructors
	public Exercise() {
		this.users = new ArrayList<User>();
		this.types = new ArrayList<ExerciseType>();
	}
	
	public Exercise(String exercise_name, int exercise_exp, String content) {
		super();
		this.exercise_name = exercise_name;
		this.exercise_exp = exercise_exp;
		this.content = content;
		this.users = new ArrayList<User>();
		this.types = new ArrayList<ExerciseType>();
	}

	// Getters && Setters:
	public long getExercise_id() {
		return exercise_id;
	}

	public void setExercise_id(long exercise_id) {
		this.exercise_id = exercise_id;
	}

	public String getExercise_name() {
		return exercise_name;
	}

	public void setExercise_name(String exercise_name) {
		this.exercise_name = exercise_name;
	}

	public int getExercise_exp() {
		return exercise_exp;
	}

	public void setExercise_exp(int exercise_exp) {
		this.exercise_exp = exercise_exp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}

	public List<ExerciseType> getTypes() {
		return types;
	}

	public void setTypes(List<ExerciseType> types) {
		this.types = types;
	}
	
	public void addType(ExerciseType type) {
		this.types.add(type);
	}
}
