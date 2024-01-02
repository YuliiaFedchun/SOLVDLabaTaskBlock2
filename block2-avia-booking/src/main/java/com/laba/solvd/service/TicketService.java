package com.laba.solvd.service;

import com.laba.solvd.domain.Ticket;

public interface TicketService {
    Ticket create(Ticket ticket, Long passengerId, Long flightId, Long tariffId);

    void deleteByPassengerId(Long passengerId);
}
