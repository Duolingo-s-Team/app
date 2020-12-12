package com.duolingo.client.rmi.models;

import java.io.Serializable;

public class ExerciseType implements Serializable {

	private long type_id;
	private String type_name;
	
	// Constructors
	public ExerciseType() {
		// Empty Constructor
	}
	
	public ExerciseType(String type_name) {
		super();
		this.type_name = type_name;
	}
		
	public ExerciseType(long type_id, String type_name) {
		super();
		this.type_id = type_id;
		this.type_name = type_name;
	}

	// Getters && Setters:
	public long getType_id() {
		return type_id;
	}

	public void setType_id(long type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
}
