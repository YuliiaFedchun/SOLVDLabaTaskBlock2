package com.laba.solvd.xml.sax.handlers;

import com.laba.solvd.domain.Flight;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class FlightHandler extends DefaultHandler {
    private final Flight flight = new Flight();
    private StringBuilder elementValue;

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        if (qName.equals("number") || qName.equals("departureTime") ||
                qName.equals("arrivalTime")) {
            elementValue = new StringBuilder();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case ("number"):
                flight.setNumber(String.valueOf(elementValue));
                break;
            case ("departureTime"):
                flight.setDepartureTime(String.valueOf(elementValue));
                break;
            case ("arrivalTime"):
                flight.setArrivalTime(String.valueOf(elementValue));
                break;
        }
    }

    public Flight getFlightInstance() {
        return flight;
    }

}
