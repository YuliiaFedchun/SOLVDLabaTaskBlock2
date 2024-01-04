package com.laba.solvd.xml;

import com.laba.solvd.domain.Airline;
import com.laba.solvd.domain.Airport;
import com.laba.solvd.domain.Flight;
import com.laba.solvd.domain.PlaneType;
import com.laba.solvd.xml.handlers.AirlineHandler;
import com.laba.solvd.xml.handlers.AirportHandler;
import com.laba.solvd.xml.handlers.FlightHandler;
import com.laba.solvd.xml.handlers.PlaneTypeHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class XMLManager  {
    private static final Logger LOGGER = LogManager.getLogger(XMLManager.class);
    public static final String fileName = "src/main/resources/flight.xml";
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        LOGGER.info(fileName + " is valid: " + ValidatorSAX.isValid(fileName));

        File file = new File(fileName);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        AirlineHandler airlineHandler = new AirlineHandler();
        parser.parse(file, airlineHandler);
        Airline airline = airlineHandler.getAirline();
        LOGGER.info(airline.toString());

        PlaneTypeHandler planeTypeHandler = new PlaneTypeHandler();
        parser.parse(file, planeTypeHandler);
        PlaneType planeType = planeTypeHandler.getPlaneType();
        LOGGER.info(planeType.toString());

        AirportHandler airportHandler = new AirportHandler();
        parser.parse(file, airportHandler);
        Airport departureAirport = airportHandler.getDepartureAirport();
        Airport arrivalAirport = airportHandler.getArrivalAirport();
        LOGGER.info(departureAirport.toString());
        LOGGER.info(arrivalAirport.toString());

        FlightHandler flightHandler = new FlightHandler();
        parser.parse(file, flightHandler);
        Flight flight = flightHandler.getFlightInstance();
        flight.setAirline(airline);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setPlaneType(planeType);
        LOGGER.info(flight.toString());

    }
}
