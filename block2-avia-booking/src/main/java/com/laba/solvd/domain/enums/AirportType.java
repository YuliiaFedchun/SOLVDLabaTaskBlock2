package com.laba.solvd.domain.enums;

public enum AirportType {
    INTERNATIONAL(1L, "international"),
    REGIONAL(2L, "regional"),
    HUB(3L, "hub");

    private final Long id;
    private final String name;

    AirportType(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

}
