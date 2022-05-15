package Port.Out;

import exceptions.ItemNotFound;
import model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReadReservationPort {
    List<Reservation> readAll();

    Reservation readById(UUID uuid) throws ItemNotFound;
}
