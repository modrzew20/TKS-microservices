//
//import exceptions.LoginInUseException;
//import model.AccessLevel;
//import model.Lane;
//import model.Reservation;
//import model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import service.LaneService;
//import service.ReservationService;
//import service.UserService;
//import static org.junit.jupiter.api.Assertions.*;
//import java.time.LocalDateTime;
//
//@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class ReservationServiceTest {
//
//    ReservationService reservationService;
//    LaneService laneService;
//    UserService userService;
//    Lane lane;
//    User user;
//    Reservation reservation;
//    Reservation reservation1;
//    LocalDateTime localDateTime;
//
//    @BeforeEach
//    void init() throws LoginInUseException {
////        reservationService = new ReservationService();
////        laneService = new LaneService();
////        userService = new UserService();
//
//        lane = laneService.addLane("vip");
//        user = userService.addUser(AccessLevel.Client,"testimie","testhaslo");
//        localDateTime = LocalDateTime.now();
//        reservation = reservationService.addReservation(user.getUuid(),lane.getUuid(), localDateTime, localDateTime.plusHours(3));
//        reservation1 = reservationService.addReservation(user.getUuid(),lane.getUuid(), localDateTime.minusMonths(1), localDateTime.plusHours(3).minusMonths(1));
//    }
//
//    @Test
//    void addReservationTest() {
//        int size = reservationService.readAllReservation().size();
//        localDateTime = localDateTime.plusDays(2);
//        reservation = reservationService.addReservation(user.getUuid(),lane.getUuid(), localDateTime, localDateTime.plusHours(3));
//        assertEquals(size+1,reservationService.readAllReservation().size());
//    }
//
//    @Test
//    void ClientsReservationTest() {
//        assertEquals(reservationService.pastClientReservations(user.getUuid()).size(),1);
//        assertEquals(reservationService.presentClientReservations(user.getUuid()).size(),1);
//    }
//
//    @Test
//    void LaneReservationTest() {
//        assertEquals(reservationService.pastLaneReservations(lane.getUuid()).size(),1);
//        assertEquals(reservationService.presentLaneReservations(lane.getUuid()).size(),1);
//    }
//
//    @Test
//    void endReservationTest() {
//        LocalDateTime localDateTime1 = LocalDateTime.now();
//        reservationService.endReservation(reservation.getUuid(),localDateTime1);
//        assertEquals(reservationService.readOneReservation(reservation.getUuid()).getEndReservation(),localDateTime1);
//    }
//
//    @Test
//    void deleteReservationTest() {
//        reservationService.endReservation(reservation.getUuid(),LocalDateTime.now());
//        reservationService.delete(reservation.getUuid());
//        assertNull(reservationService.readOneReservation(reservation.getUuid()));
//    }
//
//}