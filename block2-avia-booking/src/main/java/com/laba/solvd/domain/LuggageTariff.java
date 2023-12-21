package com.laba.solvd.domain;

public class LuggageTariff {
    private long id;
    private double handLuggagePrice;
    private double registerLuggagePrice;

    public LuggageTariff(double handLuggagePrice, double registerLuggagePrice) {
        this.handLuggagePrice = handLuggagePrice;
        this.registerLuggagePrice = registerLuggagePrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getHandLuggagePrice() {
        return handLuggagePrice;
    }

    public void setHandLuggagePrice(double handLuggagePrice) {
        this.handLuggagePrice = handLuggagePrice;
    }

    public double getRegisterLuggagePrice() {
        return registerLuggagePrice;
    }

    public void setRegisterLuggagePrice(double registerLuggagePrice) {
        this.registerLuggagePrice = registerLuggagePrice;
    }
}
