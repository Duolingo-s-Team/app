package com.duolingo.client.rmi.interfaces;

import java.util.List;

import com.duolingo.client.rmi.models.ExerciseType;

public interface IExerciseType {

	public List<ExerciseType> getAllExerciseTypes();
	
	public ExerciseType getExerciseTypeById(long exerciseType_id);
	
	public boolean deleteExerciseType(ExerciseType exerciseType);
	
	public ExerciseType insertExerciseType(ExerciseType exerciseType);
	
	public ExerciseType updateExerciseType(ExerciseType exerciseType);
}
