package repository;


import exceptions.ItemNotFound;
import modelEnt.LANE_TYPE_Ent;
import modelEnt.LaneEnt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LaneRepository implements RepositoryInterface<LaneEnt> {

    private final List<LaneEnt> laneList;

    public LaneRepository() {
        this.laneList = new ArrayList<>();
        this.create(new LaneEnt(UUID.fromString("9f77375c-7d30-4eca-a830-aebec2ddd8a7"), LANE_TYPE_Ent.normal));
        this.create(new LaneEnt(UUID.fromString("c87d6205-a804-4f51-9640-29c58ac7a184"), LANE_TYPE_Ent.premium));
        this.create(new LaneEnt(UUID.fromString("8cb3f9de-078c-4c83-97a9-ba120c12b67d"), LANE_TYPE_Ent.vip));
        this.create(new LaneEnt(UUID.fromString("c3544044-2be2-4c3b-9fe7-3be0522c158f"), LANE_TYPE_Ent.normal));
        this.create(new LaneEnt(UUID.fromString("759df6cf-66b2-4ea7-b20a-c93eee1d0e2b"), LANE_TYPE_Ent.premium));
        this.create(new LaneEnt(UUID.fromString("3ccd5052-356c-4b17-8ec7-e2b1ecb57ee3"), LANE_TYPE_Ent.vip));
    }

    private static boolean checkIfExists(List<LaneEnt> list, UUID uuid) {
        return list.stream().anyMatch(lane -> lane.getUuid().equals(uuid));
    }

    @Override
    public List<LaneEnt> readAll() {
        return laneList;
    }

    @Override
    public LaneEnt readById(UUID uuid) throws ItemNotFound {
        return laneList.stream().filter(lane1 -> uuid.equals(lane1.getUuid())).findFirst().orElseThrow((() -> new ItemNotFound("No lane with UUID found")));
    }

    @Override
    public LaneEnt create(LaneEnt object) {
        if (object.getUuid() == null || checkIfExists(laneList, object.getUuid())) {
            UUID uuid = UUID.randomUUID();
            while (checkIfExists(laneList, uuid)) {
                uuid = UUID.randomUUID();
            }
            object.setUuid(uuid);
        }
        laneList.add(object);
        return object;
    }

    @Override
    public LaneEnt delete(UUID uuid) throws ItemNotFound {
        Optional<LaneEnt> optional = laneList.stream().filter(lane -> uuid.equals(lane.getUuid())).findFirst();
        LaneEnt lane = optional.orElseThrow(() -> new ItemNotFound("No lane with UUID found"));
        if (lane != null) laneList.remove(lane);
        return lane;
    }

    @Override
    public LaneEnt update(LaneEnt object) throws ItemNotFound {
        UUID uuid = object.getUuid();
        Optional<LaneEnt> optional = laneList.stream().filter(lane -> uuid.equals(lane.getUuid())).findFirst();
        LaneEnt lane = optional.orElseThrow(() -> new ItemNotFound("No lane with UUID found"));
        if (lane != null) lane.setType(object.getType());
        return lane;
    }

    @Override
    public LaneEnt deleteLocalObject(String login) {
        throw new UnsupportedOperationException();
    }

}
