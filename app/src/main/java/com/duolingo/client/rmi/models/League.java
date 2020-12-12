package com.duolingo.client.rmi.models;

import java.io.Serializable;

public class League implements Serializable {

	private long league_rank;
	private int league_pos;

	private User user;
	
	// Constructors
	public League() {
		// Empty Constructor
	}
	
	public League(long league_rank, int league_pos) {
		super();
		this.league_rank = league_rank;
		this.league_pos = league_pos;
	}

	// Getters && Setters:
	public long getLeague_rank() {
		return league_rank;
	}

	public void setLeague_rank(long league_rank) {
		this.league_rank = league_rank;
	}

	public int getLeague_pos() {
		return league_pos;
	}

	public void setLeague_pos(int league_pos) {
		this.league_pos = league_pos;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
