package com.duolingo.client.rmi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {

	private long user_id;
	private String username;
	private String password;
	private int coins;
	private String userAvatar;
	private int userWinstreak;
	private String userEmail;

    private League league;
	private List<Course> courses;
	private List<Exercise> exercises;
	private List<Follower> followers;
	private List<Following> following;
	
	// Constructors
	public User() {
		this.courses = new ArrayList<Course>();
		this.exercises = new ArrayList<Exercise>();
		this.followers = new ArrayList<Follower>();
		this.following = new ArrayList<Following>();
	}
	
	public User(String username, String password, int coins, String userAvatar, int userWinstreak, String userEmail) {
		super();
		this.username = username;
		this.password = password;
		this.coins = coins;
		this.userAvatar = userAvatar;
		this.userWinstreak = userWinstreak;
		this.userEmail = userEmail;
		this.courses = new ArrayList<Course>();
		this.exercises = new ArrayList<Exercise>();
		this.followers = new ArrayList<Follower>();
		this.following = new ArrayList<Following>();
	}
	
	public User(long user_id, String username, String password, int coins, String userAvatar, int userWinstreak, String userEmail) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.coins = coins;
		this.userAvatar = userAvatar;
		this.userWinstreak = userWinstreak;
		this.userEmail = userEmail;
		this.courses = new ArrayList<Course>();
		this.exercises = new ArrayList<Exercise>();
		this.followers = new ArrayList<Follower>();
		this.following = new ArrayList<Following>();
	}

	// Getters && Setters:
	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public int getUserWinstreak() {
		return userWinstreak;
	}

	public void setUserWinstreak(int userWinstreak) {
		this.userWinstreak = userWinstreak;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public void addExercise(Exercise exercise) {
		this.exercises.add(exercise);
	}
	
	public List<Follower> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Follower> followers) {
		this.followers = followers;
	}
	
	public void addFollower(Follower follower) {
		this.followers.add(follower);
	}

	public List<Following> getFollowing() {
		return following;
	}

	public void setFollowingUsers(List<Following> following) {
		this.following = following;
	}
	
	public void addFollowingUser(Following following) {
		this.following.add(following);
	}

	public League getLeague() {
		return league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

}
