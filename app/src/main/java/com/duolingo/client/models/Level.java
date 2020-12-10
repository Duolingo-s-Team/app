package com.duolingo.client.models;

public class Level {

    private long level_id;

    private String level_name;

    private boolean isComplete;

    // Constructors
    public Level() {
        // Empty Constructor
    }

    public Level(String level_name) {
        super();
        this.level_name = level_name;
    }

    public Level(long level_id, String level_name) {
        super();
        this.level_id = level_id;
        this.level_name = level_name;
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
}
