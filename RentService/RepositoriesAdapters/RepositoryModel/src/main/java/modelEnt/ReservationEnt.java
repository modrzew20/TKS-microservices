package modelEnt;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReservationEnt {

    private UUID uuid;
    private LaneEnt lane;
    private UserEnt client;
    private LocalDateTime startReservation, endReservation;

    public ReservationEnt(UUID uuid, LaneEnt lane, UserEnt client, LocalDateTime startReservation, LocalDateTime endReservation) {
        this.uuid = uuid;
        this.lane = lane;
        this.client = client;
        this.startReservation = startReservation;
        this.endReservation = endReservation;
    }

    public LaneEnt getLane() {
        return lane;
    }

    public void setLane(LaneEnt lane) {
        this.lane = lane;
    }

    public UserEnt getClient() {
        return client;
    }

    public void setClient(UserEnt client) {
        this.client = client;
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
