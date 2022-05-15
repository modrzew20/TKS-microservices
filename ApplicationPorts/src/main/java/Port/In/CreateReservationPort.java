package Port.In;

import model.Reservation;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CreateReservationPort {
    Reservation create(Reservation reservation);

    boolean reservedLine(UUID uuid, LocalDateTime start, LocalDateTime end);
}
