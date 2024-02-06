package com.laba.solvd.service.impl;

import com.laba.solvd.domain.Flight;
import com.laba.solvd.domain.Passenger;
import com.laba.solvd.domain.Tariff;
import com.laba.solvd.domain.Ticket;
import com.laba.solvd.persistence.MybatisConfig;
import com.laba.solvd.persistence.repository.TicketRepository;
import com.laba.solvd.service.FlightService;
import com.laba.solvd.service.PassengerService;
import com.laba.solvd.service.TariffService;
import com.laba.solvd.service.TicketService;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TicketServiceImpl implements TicketService {
    private static final Logger LOGGER = LogManager.getLogger(TicketServiceImpl.class);
    private final TicketRepository ticketRepository;
    private final PassengerService passengerService;
    private final FlightService flightService;
    private final TariffService tariffService;

    public TicketServiceImpl(String dbType, String type) {
        //this.ticketRepository = new TicketRepositoryImpl();
        SqlSession sqlSession = MybatisConfig.getSessionFactory().openSession(true);
        this.ticketRepository = sqlSession.getMapper(TicketRepository.class);
        this.passengerService = new PassengerServiceImpl(dbType, type);
        this.flightService = new FlightServiceImpl();
        this.tariffService = new TariffServiceImpl();
    }

    @Override
    public Ticket create(Ticket ticket, Long passengerId, Long flightId, Long tariffId) {
        ticket.setId(0);
        Passenger passenger = ticket.getPassenger();
        Flight flight = ticket.getFlight();
        if (passenger != null &&
                passengerService.findById(passengerId).equals(passenger)) {
            if (ticket.getFlight() != null &&
                    flightService.findById(flightId).equals(flight)) {
                if (ticket.getTariff() != null &&
                        tariffService.findByName(ticket.getTariff().getName(),
                                flight.getAirline().getId()) != null) {
                    ticketRepository.create(ticket, passengerId, flightId, tariffId);
                } else {
                    LOGGER.info("Incorrect tariff");
                }
            } else {
                LOGGER.info("Incorrect flight");
            }
        } else {
            LOGGER.info("Incorrect passenger");
        }
        if (ticket.getTariff() != null) {
            Tariff tariff = tariffService.findByName(ticket.getTariff().getName(),
                    flight.getAirline().getId());
            if (tariff != null) {
                ticket.setTariff(tariff);
            } else {
                LOGGER.info("Incorrect tariff");
            }
        }

        return ticket;
    }

    @Override
    public void deleteByPassengerId(Long passengerId) {
        ticketRepository.deleteByPassengerId(passengerId);
    }
}
