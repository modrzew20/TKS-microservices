package modelEnt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
public class ReservationEnt {

    @Getter @Setter
    private UUID uuid;
    @Getter @Setter
    private LaneEnt lane;
    @Getter @Setter
    private UserEnt user;
    @Getter @Setter
    private LocalDateTime startReservation, endReservation;

}
