package ServicePort;

import exceptions.ItemNotFound;

import java.util.List;
import java.util.UUID;

public interface LaneServicePort<T> {

    List<T> readAllLane();

    T addLane(String lane_type);

    T updateLane(UUID uuid, String lane_type) throws ItemNotFound;

    T readOneLane(UUID uuid) throws ItemNotFound;

    T deleteLine(UUID uuid) throws ItemNotFound;
}
