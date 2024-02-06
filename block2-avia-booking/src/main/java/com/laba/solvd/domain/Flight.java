package com.laba.solvd.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.laba.solvd.domain.listener.ListenersHolder;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlTransient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@XmlAccessorType(XmlAccessType.FIELD)
public class Flight {
    private static final Logger LOGGER = LogManager.getLogger(Flight.class);
    @JsonIgnore
    @XmlTransient
    private long id;
    private String number;
    private String departureTime;
    private String arrivalTime;
    private Airline airline;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private PlaneType planeType;

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Airline getAirline() {
        return airline;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public PlaneType getPlaneType() {
        return planeType;
    }

    public void cancel() {
        ListenersHolder.onFlightCancellation(this);
        LOGGER.info(String.format("Flight '%s' was cancelled.", number));
    }

    @Override
    public String toString() {
        return "Flight{" +
                "number='" + number + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", airline=" + airline.toString() +
                '}';
    }

    public static FlightBuilder builder(Flight flight) {
        return new FlightBuilder(flight);
    }

    public static class FlightBuilder {
        private final Flight flight;

        public FlightBuilder(Flight flight) {
            this.flight = flight;
        }

        public FlightBuilder id(long id) {
            flight.id = id;
            return this;
        }

        public FlightBuilder number(String number) {
            flight.number = number;
            return this;
        }

        public FlightBuilder departureTime(String departureTime) {
            flight.departureTime = departureTime;
            return this;
        }

        public FlightBuilder arrivalTime(String arrivalTime) {
            flight.arrivalTime = arrivalTime;
            return this;
        }

        public FlightBuilder airline(Airline airline) {
            flight.airline = airline;
            return this;
        }

        public FlightBuilder departureAirport(Airport departureAirport) {
            flight.departureAirport = departureAirport;
            return this;
        }

        public FlightBuilder arrivalAirport(Airport arrivalAirport) {
            flight.arrivalAirport = arrivalAirport;
            return this;
        }

        public FlightBuilder planeType(PlaneType planeType) {
            flight.planeType = planeType;
            return this;
        }

        public Flight build() {
            return flight;
        }
    }

}
