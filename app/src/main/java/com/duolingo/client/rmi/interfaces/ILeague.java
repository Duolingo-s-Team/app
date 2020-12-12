package com.duolingo.client.rmi.interfaces;

import java.util.List;

import com.duolingo.client.rmi.models.League;

public interface ILeague {

	public List<League> getAllLeagueRanks();
	
	public League getRankByUserId(long user_id);
	
	public boolean deleteLeague(League league);
	
	public League insertLeague(League league);
	
	public League updateLeague(League league);
}
