package com.duolingo.client.rmi.models;

import java.io.Serializable;

public class Follower implements Serializable {

	private long user_id;
	
	// Constructors
	public Follower() {
		// Empty Constructor
	}
	
	public Follower(long user_id) {
		super();
		this.user_id = user_id;
	}

	// Getters && Setters:
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

}
