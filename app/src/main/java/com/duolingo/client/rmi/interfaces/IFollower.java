package com.duolingo.client.rmi.interfaces;

import java.util.List;

import com.duolingo.client.rmi.models.Follower;

public interface IFollower {

	public List<Follower> getAllFollowers();
	
	public Follower getFollowerById(long follower_id);
	
	public boolean deleteFollower(Follower follower);
	
	public Follower insertFollower(Follower follower);
	
	public Follower updateFollower(Follower follower);
}
