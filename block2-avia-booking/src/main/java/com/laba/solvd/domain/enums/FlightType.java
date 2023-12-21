package com.laba.solvd.domain.enums;

public enum FlightType {
    REGULAR(1, "regular"),
    LOWCOST(2, "lowcost"),
    CHARTER(3, "charter");

    private long id;
    private String type;

    FlightType(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
