package com.laba.solvd.domain;

public class Ticket {
    private long id;
    private Passenger passenger;
    private Flight flight;
    private int addHandLuggage;
    private int addRegisterLuggage;
    private Tariff tariff;
    private double price;

    public Ticket(Passenger passenger, Flight flight, int addHandLuggage,
                  int addRegisterLuggage, Tariff tariff, double price) {
        this.passenger = passenger;
        this.flight = flight;
        this.addHandLuggage = addHandLuggage;
        this.addRegisterLuggage = addRegisterLuggage;
        this.tariff = tariff;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getAddHandLuggage() {
        return addHandLuggage;
    }

    public void setAddHandLuggage(int addHandLuggage) {
        this.addHandLuggage = addHandLuggage;
    }

    public int getAddRegisterLuggage() {
        return addRegisterLuggage;
    }

    public void setAddRegisterLuggage(int addRegisterLuggage) {
        this.addRegisterLuggage = addRegisterLuggage;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
