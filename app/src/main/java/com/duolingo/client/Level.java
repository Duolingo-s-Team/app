package com.duolingo.client;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Level {

    private long level_id;

    private String level_name;

    private boolean isComplete;

    private List<Exercise> exercises;

    // Constructors


    public Level() {
        this.exercises = new ArrayList<Exercise>();
    }

    public Level(String level_name) {
        super();
        this.level_name = level_name;
    }

    public Level(String level_name, boolean isComplete) {
        this.level_name = level_name;
        this.isComplete = isComplete;
    }

    public long getLevel_id() {
        return level_id;
    }

    public void setLevel_id(long level_id) {
        this.level_id = level_id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
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

}
