package com.laba.solvd.persistence.repository;

import com.laba.solvd.domain.Ticket;
import org.apache.ibatis.annotations.Param;

public interface TicketRepository {
    void create(@Param("ticket") Ticket ticket,
                @Param("passengerId") Long passengerId,
                @Param("flightId") Long flightId,
                @Param("tariffId") Long tariffId);

    void deleteByPassengerId(Long passengerId);
}
