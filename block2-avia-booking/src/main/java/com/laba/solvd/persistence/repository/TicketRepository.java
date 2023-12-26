package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Ticket;

public interface TicketRepository {
    void create(Ticket ticket);

    void deleteByPassengerId(Long passengerId);
}
