package repositoryTests;

import exceptions.ItemNotFound;
import modelEnt.LANE_TYPE_Ent;
import modelEnt.LaneEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LaneRepository;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class LaneRepositoryTest {

    LaneRepository laneRepository;

    @BeforeEach
    void init() {
        laneRepository = new LaneRepository();
    }


    @Test
    void createLaneTest() {
        int size = laneRepository.readAll().size();
        laneRepository.create(new LaneEnt(UUID.randomUUID(), LANE_TYPE_Ent.normal));
        assertEquals(laneRepository.readAll().size(),size + 1);
    }

    @Test
    void deleteLaneTest() throws ItemNotFound {
        int size = laneRepository.readAll().size();
        UUID uuid = UUID.randomUUID();
        laneRepository.create(new LaneEnt(uuid, LANE_TYPE_Ent.normal));
        laneRepository.delete(uuid);
        assertEquals(laneRepository.readAll().size(),size);
    }

    @Test
    void doNotFoundLaneToDelete() {
        assertThrows(ItemNotFound.class,()->laneRepository.delete(UUID.randomUUID()));
    }

    @Test
    void readByIdLaneTest() throws ItemNotFound {
        UUID uuid = UUID.randomUUID();
        laneRepository.create(new LaneEnt(uuid, LANE_TYPE_Ent.normal));
        LaneEnt result = laneRepository.readById(uuid);
        assertEquals(result.getUuid(),uuid);
        assertEquals(result.getType(),LANE_TYPE_Ent.normal);
    }

    @Test
    void doNotFoundLaneToReadById() {
        assertThrows(ItemNotFound.class,()->laneRepository.readById(UUID.randomUUID()));
    }

    @Test
    void updateLaneTest() throws ItemNotFound {
        UUID uuid = UUID.randomUUID();
        laneRepository.create(new LaneEnt(uuid, LANE_TYPE_Ent.normal));
        laneRepository.update(new LaneEnt(uuid,LANE_TYPE_Ent.vip));
        LaneEnt result = laneRepository.readById(uuid);
        assertEquals(result.getUuid(),uuid);
        assertEquals(result.getType(),LANE_TYPE_Ent.vip);
    }

}
