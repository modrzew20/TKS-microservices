package applicationcontroller.modelRest.modelView;

import java.util.Objects;
import java.util.UUID;

public class LaneView {

    private UUID uuid;
    private LANE_TYPE_View type;

    public LaneView(UUID uuid, LANE_TYPE_View type) {
        this.uuid = uuid;
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LANE_TYPE_View getType() {
        return type;
    }

    public void setType(LANE_TYPE_View type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Lane{" +
                "uuid=" + uuid +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LaneView)) return false;
        LaneView lane = (LaneView) o;
        return Objects.equals(getUuid(), lane.getUuid()) && getType() == lane.getType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getType());
    }
}

