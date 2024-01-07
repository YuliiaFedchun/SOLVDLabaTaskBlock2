package com.laba.solvd.domain;

import com.laba.solvd.domain.enums.FlightType;
import com.laba.solvd.xml.jaxb.adapters.FlightTypeAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
public class Airline {
    @XmlTransient
    private long id;
    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "code")
    private String code;

    @XmlElementWrapper(name = "flightTypes")
    @XmlElement(name = "flightType")
    @XmlJavaTypeAdapter(FlightTypeAdapter.class)
    private List<FlightType> flightTypes;
    private LuggageTariff luggageTariff;

    public Airline(String name, String code, List<FlightType> flightTypes) {
        this.name = name;
        this.code = code;
        this.flightTypes = flightTypes;
    }

    public Airline() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<FlightType> getFlightTypes() {
        return flightTypes;
    }

    public void setFlightTypes(List<FlightType> flightTypes) {
        this.flightTypes = flightTypes;
    }

    public LuggageTariff getLuggageTariff() {
        return luggageTariff;
    }

    public void setLuggageTariff(LuggageTariff luggageTariff) {
        this.luggageTariff = luggageTariff;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", flightTypes='" + flightTypes.toString() + '\'' +
                ", luggageTariff='" + luggageTariff.toString() + '\'' +
                '}';
    }
}
