package adapters;

import Port.In.CreateReservationPort;
import Port.In.DeleteReservationPort;
import Port.In.UpdateReservationPort;
import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.ReservationRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import static converters.ReservationConverter.convertFromReservation;
import static converters.ReservationConverter.convertToReservation;

@Component
public class ReservationPortInAdapter implements CreateReservationPort, DeleteReservationPort, UpdateReservationPort {

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation create(Reservation reservation) {
        return convertToReservation(reservationRepository.create(convertFromReservation(reservation)));
    }

    @Override
    public Reservation delete(UUID uuid) throws ItemNotFound, CannotDeleteItem {
        return convertToReservation(reservationRepository.delete(uuid));
    }

    @Override
    public Reservation update(Reservation reservation) {
        return convertToReservation(reservationRepository.update(convertFromReservation(reservation)));
    }

    @Override
    public Reservation endReservation(UUID uuid, LocalDateTime localDateTime) {
        return convertToReservation(reservationRepository.endReservation(uuid, localDateTime));
    }

    @Override
    public boolean reservedLine(UUID uuid, LocalDateTime start, LocalDateTime end) {
        return reservationRepository.reservedLine(uuid, start, end);
    }
}
