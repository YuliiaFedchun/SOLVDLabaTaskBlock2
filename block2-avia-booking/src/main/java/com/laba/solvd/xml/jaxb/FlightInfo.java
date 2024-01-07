package com.laba.solvd.xml.jaxb;

import com.laba.solvd.domain.Flight;
import com.laba.solvd.xml.jaxb.adapters.DateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;


@XmlRootElement(name = "flight-info")
@XmlAccessorType(XmlAccessType.FIELD)
public class FlightInfo {

    @XmlJavaTypeAdapter(DateAdapter.class)
    private LocalDate issueDate;
    private Flight flight;
    private int ticketsNumber;

    public FlightInfo(LocalDate date, Flight flight, int ticketsNumber) {
        this.issueDate = date;
        this.flight = flight;
        this.ticketsNumber = ticketsNumber;
    }

    public FlightInfo() {}

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getTicketsNumber() {
        return ticketsNumber;
    }

    public void setTicketsNumber(int ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "issueDate=" + issueDate.toString() +
                ", flight=" + flight.toString() +
                ", ticketsNumber=" + ticketsNumber +
                '}';
    }
}
