package com.laba.solvd.domain.enums;

public enum ServiceClass {
    BUSINESS(1, "business"),
    ECONOMY(2, "economy");

    private long id;
    private String name;

    ServiceClass(long id, String name) {
        this.id = id;
        this.name = name;
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
