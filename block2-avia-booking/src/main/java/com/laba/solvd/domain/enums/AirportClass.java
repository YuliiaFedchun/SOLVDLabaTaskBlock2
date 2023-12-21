package com.laba.solvd.domain.enums;

public enum AirportClass {
    INTERNATIONAL(1L, "international"),
    REGIONAL(2L, "regional"),
    HUB(3L, "hub");

    private final long id;
    private final String name;
    AirportClass(long id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
