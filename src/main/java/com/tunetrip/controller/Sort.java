package com.tunetrip.controller;

public enum Sort {

    DATE("date"), //
    VIEW_COUNT("viewCount"), //
    TITLE("title"), //
    RATING("rating");

    private final String type;

    Sort(String type) {
        this.type = type;
    }

}
