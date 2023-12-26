package com.laba.solvd.service;

import com.laba.solvd.domain.Ticket;

public interface TicketService {
    Ticket create(Ticket ticket);

    void deleteByPassengerId(Long passengerId);
}
