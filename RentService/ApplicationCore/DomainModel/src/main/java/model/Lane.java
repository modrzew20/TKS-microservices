package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@ToString
public class Lane {

    @Getter
    @Setter
    private UUID uuid;
    @Getter @Setter
    private LANE_TYPE type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lane)) return false;
        Lane lane = (Lane) o;
        return Objects.equals(getUuid(), lane.getUuid()) && getType() == lane.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getType());
    }
}

