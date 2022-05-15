package adapters;

import Port.Out.ClientsReservationPort;
import Port.Out.LanesReservationPort;
import Port.Out.ReadReservationPort;
import exceptions.ItemNotFound;
import model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ReservationRepository;

import java.util.List;
import java.util.UUID;

import static converters.ReservationConverter.convertToListReservation;
import static converters.ReservationConverter.convertToReservation;

@Component
public class ReservationPortOutAdapter implements LanesReservationPort, ReadReservationPort, ClientsReservationPort {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public List<Reservation> readAll() {
        return convertToListReservation(reservationRepository.readAll());
    }

    @Override
    public Reservation readById(UUID uuid) throws ItemNotFound {
        return convertToReservation(reservationRepository.readById(uuid));
    }

    @Override
    public List<Reservation> pastClientReservations(UUID clientsUUID) {
        return convertToListReservation(reservationRepository.pastClientReservations(clientsUUID));
    }

    @Override
    public List<Reservation> presentClientReservations(UUID clientsUUID) {
        return convertToListReservation(reservationRepository.presentClientReservations(clientsUUID));
    }

    @Override
    public List<Reservation> pastLaneReservations(UUID laneUUID) {
        return convertToListReservation(reservationRepository.pastLaneReservations(laneUUID));
    }

    @Override
    public List<Reservation> presentLaneReservations(UUID laneUUID) {
        return convertToListReservation(reservationRepository.presentLaneReservations(laneUUID));
    }
}
