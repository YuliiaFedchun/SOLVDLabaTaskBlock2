package com.laba.solvd.domain.listener;

import com.laba.solvd.domain.Flight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PassengerListener implements FlightListener {
    private static final Logger LOGGER = LogManager.getLogger(PassengerListener.class);

    @Override
    public void onFlightCancellation(Flight flight) {
        LOGGER.info(String.format("Your flight '%s' was canceled. You may choose another one " +
                "or get your money back", flight.getNumber()));
    }
}
