package com.laba.solvd.domain.listener;

import com.laba.solvd.domain.Flight;

public interface FlightListener {
    void onFlightCancellation(Flight flight);
}
