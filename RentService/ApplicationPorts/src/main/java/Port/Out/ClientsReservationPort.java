package Port.Out;

import model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ClientsReservationPort {
    List<Reservation> pastClientReservations(UUID clientsUUID);

    List<Reservation> presentClientReservations(UUID clientsUUID);
}
