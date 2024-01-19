package com.laba.solvd.domain.listener;

import com.laba.solvd.domain.Flight;

import java.util.ArrayList;
import java.util.List;

public class ListenersHolder {
    private static final List<FlightListener> flightListeners = new ArrayList<>();

    public static void subscribe(FlightListener flightListener) {
        flightListeners.add(flightListener);
    }

    public static void unsubscribe(FlightListener flightListener) {
        flightListeners.remove(flightListener);
    }

    public static void onFlightCancellation(Flight flight) {
        for (FlightListener flightListener : flightListeners) {
            flightListener.onFlightCancellation(flight);
        }
    }
}
