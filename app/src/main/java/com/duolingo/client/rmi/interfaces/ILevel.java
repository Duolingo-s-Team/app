package com.duolingo.client.rmi.interfaces;

import java.util.List;

import com.duolingo.client.rmi.models.Level;

public interface ILevel {

	public List<Level> getAllLevels();
	
	public Level getLevelById(long level_id);
	
	public Level getLevelByName(String level_name);
	
	public List<Level> getLevelsByCategoryId(long category_id);
	
	public boolean deleteLevel(Level level);
	
	public Level insertLevel(Level level);
	
	public Level updateLevel(Level level);
}
