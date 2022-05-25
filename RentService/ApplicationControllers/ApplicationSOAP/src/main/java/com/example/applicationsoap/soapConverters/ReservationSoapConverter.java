package com.example.applicationsoap.soapConverters;

import com.example.applicationsoap.soapmodel.reservationmodel.ReservationSoap;
import model.Reservation;

public class ReservationSoapConverter {

    public static ReservationSoap convertFromReservation(Reservation reservation) {
        if (reservation == null ) return null;
        ReservationSoap reservationSoap = new ReservationSoap();
        reservationSoap.setUuid(reservation.getUuid().toString());
        reservationSoap.setUserUuid(reservation.getUser().toString());
        reservationSoap.setLaneUuid(reservation.getLane().getUuid().toString());
        reservationSoap.setStartReservation(reservation.getStartReservation().toString());
        reservationSoap.setEndReservation(reservation.getEndReservation().toString());
        return reservationSoap;
    }

}
