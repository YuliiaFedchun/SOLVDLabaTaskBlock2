package com.laba.solvd.xml.sax.handlers;

import com.laba.solvd.domain.PlaneType;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


public class PlaneTypeHandler extends DefaultHandler {
    private final PlaneType planeType = new PlaneType();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) {
        if (qName.equals("planeType")) {
            planeType.setName(attributes.getValue("name"));
            planeType.setSeatsNumber(Integer.parseInt(attributes.getValue("seatsNumber")));
        }

    }

    public PlaneType getPlaneType() {
        return planeType;
    }

}
