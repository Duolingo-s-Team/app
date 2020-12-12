package com.duolingo.client.rmi.interfaces;

import java.util.List;

import com.duolingo.client.rmi.models.Following;

public interface IFollowing {

	public List<Following> getAllFollowingUsers();
	
	public Following getFollowingById(long following_id);
	
	public boolean deleteFollowing(Following following);
	
	public Following insertFollowing(Following following);
	
	public Following updateFollowing(Following following);
}
