package com.laba.solvd.domain.enums;

public enum FlightType {
    REGULAR(1, "regular"),
    LOWCOST(2, "lowcost"),
    CHARTER(3, "charter");

    private long id;

    private String name;

    FlightType(long id, String type) {
        this.id = id;
        this.name = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
