package com.duolingo.client;

import java.io.File;
import java.io.FileInputStream;

public class Exercise {

    private long exercise_id;

    private String exercise_name;
    private int exercise_exp;
    private byte[] content;

    // Constructors
    public Exercise() {
        // Empty Constructor
    }

    public Exercise(String exercise_name, int exercise_exp, byte[] content) {
        super();
        this.exercise_name = exercise_name;
        this.exercise_exp = exercise_exp;
        this.content = content;
    }

    // Getters && Setters:
    public long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public int getExercise_exp() {
        return exercise_exp;
    }

    public void setExercise_exp(int exercise_exp) {
        this.exercise_exp = exercise_exp;
    }

    public byte[] getContent() {
        return content;
    }

}
