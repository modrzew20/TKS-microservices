package Port.In;

import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import model.Reservation;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DeleteReservationPort {
    Reservation delete(UUID uuid) throws ItemNotFound, CannotDeleteItem;

    Reservation endReservation(UUID uuid, LocalDateTime localDateTime);
}
