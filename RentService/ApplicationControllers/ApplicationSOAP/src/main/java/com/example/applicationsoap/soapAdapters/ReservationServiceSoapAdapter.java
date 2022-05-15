package com.example.applicationsoap.soapAdapters;

import ServicePort.ReservationServicePort;
import com.example.applicationsoap.soapConverters.ReservationSoapConverter;
import com.example.applicationsoap.soapmodel.reservationmodel.ReservationSoap;
import exceptions.CannotCreateItem;
import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ReservationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceSoapAdapter implements ReservationServicePort<ReservationSoap> {

    @Autowired
    ReservationService reservationService;

    @Override
    public List<ReservationSoap> readAllReservation() {
        return reservationService.readAllReservation().stream().map(ReservationSoapConverter::convertFromReservation).toList();
    }

    @Override
    public ReservationSoap addReservation(UUID clientsUUID, UUID laneUUID, LocalDateTime start, LocalDateTime end) throws ItemNotFound, CannotCreateItem {
        return ReservationSoapConverter.convertFromReservation(reservationService.addReservation(clientsUUID, laneUUID, start, end));
    }

    @Override
    public ReservationSoap readOneReservation(UUID uuid) throws ItemNotFound {
        return ReservationSoapConverter.convertFromReservation(reservationService.readOneReservation(uuid));
    }

    @Override
    public List<ReservationSoap> pastClientReservations(UUID clientsUUID) {
        return null;
    }

    @Override
    public List<ReservationSoap> presentClientReservations(UUID clientsUUID) {
        return null;
    }

    @Override
    public List<ReservationSoap> pastLaneReservations(UUID laneUUID) {
        return null;
    }

    @Override
    public List<ReservationSoap> presentLaneReservations(UUID laneUUID) {
        return null;
    }

    @Override
    public ReservationSoap endReservation(UUID uuid, LocalDateTime localDateTime) {
        return ReservationSoapConverter.convertFromReservation(reservationService.endReservation(uuid,localDateTime));
    }

    @Override
    public ReservationSoap delete(UUID uuid) throws ItemNotFound, CannotDeleteItem {
        return ReservationSoapConverter.convertFromReservation(reservationService.delete(uuid));
    }
}
