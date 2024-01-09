package com.laba.solvd.xml.sax.handlers;

import com.laba.solvd.domain.Airport;
import com.laba.solvd.domain.enums.AirportType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class AirportHandler extends DefaultHandler {
    private Airport departureAirport;
    private Airport arrivalAirport;

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        String name = null;
        String city = null;
        String country = null;
        AirportType airportType = null;

        if (qName.equals("departureAirport") || qName.equals("arrivalAirport")) {
            name = attributes.getValue("name");
            city = attributes.getValue("city");
            country = attributes.getValue("country");
            airportType = AirportType.valueOf(attributes.getValue("typeName").toUpperCase());
        }
        if (qName.equals("departureAirport")) {
            departureAirport = new Airport(name, city, country, airportType);
        }
        if (qName.equals("arrivalAirport")) {
            arrivalAirport = new Airport(name, city, country, airportType);
        }
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }
}
