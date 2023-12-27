package com.laba.solvd.domain.enums;

public enum ServiceClass {
    BUSINESS(1L, "business"),
    ECONOMY(2L, "economy");

    private Long id;
    private String name;

    ServiceClass(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
