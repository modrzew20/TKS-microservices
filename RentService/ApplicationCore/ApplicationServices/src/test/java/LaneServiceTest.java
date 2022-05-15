//
//import model.Lane;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import service.LaneService;
//
//@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class LaneServiceTest {
//
//    @Autowired
//    LaneService laneService;
//
//    @BeforeEach
//    void init(LaneService laneService) {
//        laneService.addLane("vip");
//    }
//
//    @Test
//    void addLaneTest() {
//        int size = laneService.readAllLane().size();
//        laneService.addLane("vip");
//        assertEquals(size+1, laneService.readAllLane().size());
//    }
//
//    @Test
//    void updateLaneTest() {
//        Lane lane = laneService.readAllLane().get(0);
//        laneService.updateLane(lane.getUuid(),"premium");
//        assertEquals(lane.getType(),laneService.readOneLane(lane.getUuid()).getType());
//    }
//
//    @Test
//    void deleteLaneTest() {
//        Lane lane = laneService.readAllLane().get(0);
//        int size = laneService.readAllLane().size();
//        laneService.deleteLine(lane.getUuid());
//        assertEquals(size-1, laneService.readAllLane().size());
//    }
//
//
//
//
//
//}