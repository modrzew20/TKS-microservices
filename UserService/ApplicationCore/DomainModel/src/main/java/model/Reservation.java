package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reservation {

    private UUID uuid;
    private Lane lane;
    private User user;
    private LocalDateTime startReservation, endReservation;

    public Reservation(UUID uuid, Lane lane, User client, LocalDateTime startReservation, LocalDateTime endReservation) {
        this.uuid = uuid;
        this.lane = lane;
        this.user = client;
        this.startReservation = startReservation;
        this.endReservation = endReservation;
    }

    public Lane getLane() {
        return lane;
    }

    public void setLane(Lane lane) {
        this.lane = lane;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
