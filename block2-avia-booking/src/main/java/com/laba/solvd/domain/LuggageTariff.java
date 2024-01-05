package com.laba.solvd.domain;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
public class LuggageTariff {
    @XmlTransient
    private long id;
    @XmlAttribute(name = "handLuggagePrice")
    private double handLuggagePrice;
    @XmlAttribute(name = "registerLuggagePrice")
    private double registerLuggagePrice;

    public LuggageTariff(double handLuggagePrice, double registerLuggagePrice) {
        this.handLuggagePrice = handLuggagePrice;
        this.registerLuggagePrice = registerLuggagePrice;
    }

    public LuggageTariff() {
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

    @Override
    public String toString() {
        return "LuggageTariff{" +
                "handLuggagePrice=" + handLuggagePrice +
                ", registerLuggagePrice=" + registerLuggagePrice +
                '}';
    }
}
