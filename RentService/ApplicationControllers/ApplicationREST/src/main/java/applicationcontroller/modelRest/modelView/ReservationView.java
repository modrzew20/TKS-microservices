package applicationcontroller.modelRest.modelView;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationView {

    private UUID uuid;
    private LaneView lane;
    private UserView user;
    private LocalDateTime startReservation, endReservation;

    public ReservationView(UUID uuid, LaneView lane, UserView client, LocalDateTime startReservation, LocalDateTime endReservation) {
        this.uuid = uuid;
        this.lane = lane;
        this.user = client;
        this.startReservation = startReservation;
        this.endReservation = endReservation;
    }

    public LaneView getLane() {
        return lane;
    }

    public void setLane(LaneView lane) {
        this.lane = lane;
    }

    public UserView getUser() {
        return user;
    }

    public void setUser(UserView user) {
        this.user = user;
    }

    public LocalDateTime getStartReservation() {
        return startReservation;
    }

    public void setStartReservation(LocalDateTime startReservation) {
        this.startReservation = startReservation;
    }

    public LocalDateTime getEndReservation() {
        return endReservation;
    }

    public void setEndReservation(LocalDateTime endReservation) {
        this.endReservation = endReservation;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
