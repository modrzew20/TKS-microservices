package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
public class Reservation {

    @Getter
    @Setter
    private UUID uuid;
    @Getter
    @Setter
    private Lane lane;
    @Getter
    @Setter
    private User user;
    @Getter
    @Setter
    private LocalDateTime startReservation, endReservation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(getUuid(), that.getUuid()) &&
                Objects.equals(getLane(), that.getLane()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getStartReservation(), that.getStartReservation()) &&
                Objects.equals(getEndReservation(), that.getEndReservation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getLane(), getUser(), getStartReservation(), getEndReservation());
    }
}
