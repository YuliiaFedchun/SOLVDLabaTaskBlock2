package com.laba.solvd.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonValue
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

}
