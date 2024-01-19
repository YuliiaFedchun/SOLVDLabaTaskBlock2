package com.laba.solvd.domain.listener;

import com.laba.solvd.domain.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AirportListener implements FlightListener {
    private static final Logger LOGGER = LogManager.getLogger(AirportListener.class);

    @Override
    public void onFlightCancellation(Flight flight) {
        LOGGER.info(String.format("The flight '%s' was canceled", flight.getNumber()));
    }
}
