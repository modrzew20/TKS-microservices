package applicationcontroller.modelRest.modelView;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ReservationView {

    private UUID uuid;
    private LaneView lane;
    private UserView user;
    private LocalDateTime startReservation, endReservation;

}
