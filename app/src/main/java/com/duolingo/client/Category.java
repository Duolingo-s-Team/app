package com.duolingo.client;

import java.util.List;

public class Category {

    private long category_id;

    private String category_name;

    private List<Level> levels;

    // Constructors
    public Category() {
        // Empty Constructor
    }

    public Category(String category_name) {
        super();
        this.category_name = category_name;
    }

    public Category(long category_id, String category_name) {
        super();
        this.category_id = category_id;
        this.category_name = category_name;
    }

    // Getters && Setters:
    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
