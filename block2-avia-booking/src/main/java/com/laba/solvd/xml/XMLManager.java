package com.laba.solvd.xml;

import com.laba.solvd.domain.Airline;
import com.laba.solvd.domain.Airport;
import com.laba.solvd.domain.Flight;
import com.laba.solvd.domain.PlaneType;
import com.laba.solvd.xml.jaxb.FlightInfo;
import com.laba.solvd.xml.sax.ValidatorSAX;
import com.laba.solvd.xml.sax.handlers.AirlineHandler;
import com.laba.solvd.xml.sax.handlers.AirportHandler;
import com.laba.solvd.xml.sax.handlers.FlightHandler;
import com.laba.solvd.xml.sax.handlers.PlaneTypeHandler;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
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
    public static final String fileName1 = "src/main/resources/flight.xml";
    public static final String fileName2 = "src/main/resources/flight-i nfo.xml";
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        LOGGER.info(fileName1 + " is valid: " + ValidatorSAX.isValid(fileName1));

        File file1 = new File(fileName1);

        //SAX
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        AirlineHandler airlineHandler = new AirlineHandler();
        parser.parse(file1, airlineHandler);
        Airline airline = airlineHandler.getAirline();
        LOGGER.info(airline.toString());

        PlaneTypeHandler planeTypeHandler = new PlaneTypeHandler();
        parser.parse(file1, planeTypeHandler);
        PlaneType planeType = planeTypeHandler.getPlaneType();
        LOGGER.info(planeType.toString());

        AirportHandler airportHandler = new AirportHandler();
        parser.parse(file1, airportHandler);
        Airport departureAirport = airportHandler.getDepartureAirport();
        Airport arrivalAirport = airportHandler.getArrivalAirport();
        LOGGER.info(departureAirport.toString());
        LOGGER.info(arrivalAirport.toString());

        FlightHandler flightHandler = new FlightHandler();
        parser.parse(file1, flightHandler);
        Flight flight = flightHandler.getFlightInstance();
        flight.setAirline(airline);
        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setPlaneType(planeType);
        LOGGER.info(flight.toString());

        //JAXB
        File file2 = new File(fileName2);
        try {
            JAXBContext context = JAXBContext.newInstance(FlightInfo.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            FlightInfo flightInfo = (FlightInfo) unmarshaller.unmarshal(file2);
            LOGGER.info(flightInfo.toString());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }


    }
}
