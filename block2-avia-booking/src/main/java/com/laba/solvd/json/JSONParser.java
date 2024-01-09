package com.laba.solvd.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laba.solvd.xml.jaxb.FlightInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class JSONParser {
    private static final Logger LOGGER = LogManager.getLogger(JSONParser.class);
    public static final String fileName = "src/main/resources/flight-info.json";

    public static void main(String[] args) {
        File file = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();
        try {
            FlightInfo flightInfo = mapper.readValue(file, FlightInfo.class);
            LOGGER.info(flightInfo);
            LOGGER.info("Departure airport: " + flightInfo.getFlight().getDepartureAirport());
            LOGGER.info("Arrival airport: " + flightInfo.getFlight().getArrivalAirport());
            LOGGER.info(flightInfo.getFlight().getPlaneType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
