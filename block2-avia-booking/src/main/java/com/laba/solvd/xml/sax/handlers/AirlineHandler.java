package com.laba.solvd.xml.sax.handlers;

import com.laba.solvd.domain.Airline;
import com.laba.solvd.domain.LuggageTariff;
import com.laba.solvd.domain.enums.FlightType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class AirlineHandler extends DefaultHandler {
    private final Airline airline = new Airline();
    private final List<FlightType> flightTypes = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        if (qName.equals("airline")) {
            airline.setName(attributes.getValue("name"));
            airline.setCode(attributes.getValue("code"));
        }
        if (qName.equals("flightType")) {
            flightTypes.add(FlightType.valueOf(attributes.getValue("typeName").toUpperCase()));
        }
        if (qName.equals("luggageTariff")) {
            double handLuggagePrice =
                    Double.parseDouble(attributes.getValue("handLuggagePrice"));
            double registerLuggagePrice =
                    Double.parseDouble(attributes.getValue("registerLuggagePrice"));
            airline.setLuggageTariff(new LuggageTariff(handLuggagePrice, registerLuggagePrice));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("flightTypes")) {
            airline.setFlightTypes(flightTypes);
        }
    }

    public Airline getAirline() {
        return airline;
    }
}
