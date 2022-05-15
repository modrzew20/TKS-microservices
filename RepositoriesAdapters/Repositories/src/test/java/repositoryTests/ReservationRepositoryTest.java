package repositoryTests;

import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import modelEnt.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.LaneRepository;
import repository.ReservationRepository;
import repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryTest {

    static ReservationRepository reservationRepository;
    static UserRepository userRepository;
    static LaneRepository laneRepository;

    static UserEnt user1;
    static UserEnt user2;
    static LaneEnt lane1;
    static LaneEnt lane2;
    static ReservationEnt reservation1;
    static ReservationEnt reservation2;
    static ReservationEnt reservation3;
    static LocalDateTime start1;
    static LocalDateTime end1;
    static LocalDateTime start2;
    static LocalDateTime end2;
    static LocalDateTime start3;
    static LocalDateTime end3;


    @BeforeEach
    private void setup() {
        reservationRepository = new ReservationRepository();
        try {
            userRepository = new UserRepository();
        } catch (LoginInUseException e) {
            e.printStackTrace();
        }
        laneRepository = new LaneRepository();

        start1 = LocalDateTime.now().minusDays(3);
        end1 = LocalDateTime.now().minusDays(2);

        start2 = LocalDateTime.now().minusDays(1);
        end2 = LocalDateTime.now().plusDays(1);

        start3 = LocalDateTime.now();
        end3 = LocalDateTime.now().plusDays(1);


        try {
            user1 = new ClientEnt(UUID.randomUUID(), "userA", "password", true);
            userRepository.create(user1);

            user2 = new ClientEnt(UUID.randomUUID(), "userB", "password", true);
            userRepository.create(user2);
        } catch (LoginInUseException e) {
            e.printStackTrace();
        }

        lane1 = new LaneEnt(UUID.randomUUID(), LANE_TYPE_Ent.premium);
        laneRepository.create(lane1);
        lane2 = new LaneEnt(UUID.randomUUID(), LANE_TYPE_Ent.vip);
        laneRepository.create(lane2);
        reservation1 = new ReservationEnt(null, lane1, user1, start1, end1);
        reservation2 = new ReservationEnt(null, lane2, user1, start2, end2);
        reservation3 = new ReservationEnt(null, lane1, user1, start3, end3);

        reservation1 = reservationRepository.create(reservation1);
        reservation2 = reservationRepository.create(reservation2);
        reservation3 = reservationRepository.create(reservation3);
    }

    @Test
    void readAll() {
        List<ReservationEnt> list = reservationRepository.readAll();
        assertTrue(list.contains(reservation1));
        assertTrue(list.contains(reservation2));
        assertTrue(list.contains(reservation3));

    }

    @Test
    void readById() throws ItemNotFound {
        assertEquals(reservationRepository.readById(reservation1.getUuid()), reservation1);
        assertEquals(reservationRepository.readById(reservation2.getUuid()), reservation2);
        assertEquals(reservationRepository.readById(reservation3.getUuid()), reservation3);
        assertThrows(ItemNotFound.class, () -> reservationRepository.readById(UUID.randomUUID()));
    }

    @Test
    void create() {
        reservationRepository.create(new ReservationEnt(null, lane1, user1, LocalDateTime.now().minusDays(10), LocalDateTime.now().minusDays(8)));
    }

    @Test
    void delete() {
        assertThrows(ItemNotFound.class, () -> reservationRepository.delete(UUID.randomUUID()));
        assertThrows(CannotDeleteItem.class, () -> reservationRepository.delete(reservation2.getUuid()));
        assertDoesNotThrow(() -> reservationRepository.delete(reservation1.getUuid()));
        assertThrows(ItemNotFound.class, () -> reservationRepository.delete(reservation1.getUuid()));
        reservationRepository.create(reservation2);
    }

    @Test
    void update() {
        // method not allowed
        assertNull(reservationRepository.update(reservation1));
    }

    @Test
    void pastClientReservations() {
        List<ReservationEnt> list = reservationRepository.pastClientReservations(user1.getUuid());
        assertTrue(list.contains(reservation1));
        assertFalse(list.contains(reservation2));
        assertFalse(list.contains(reservation3));
    }

    @Test
    void presentClientReservations() {
        List<ReservationEnt> list = reservationRepository.presentClientReservations(user1.getUuid());
        assertFalse(list.contains(reservation1));
        assertTrue(list.contains(reservation2));
        assertTrue(list.contains(reservation3));
    }

    @Test
    void pastLaneReservations() {
        List<ReservationEnt> list = reservationRepository.pastLaneReservations(lane1.getUuid());
        assertTrue(list.contains(reservation1));
        assertFalse(list.contains(reservation2));
        assertFalse(list.contains(reservation3));
    }

    @Test
    void presentLaneReservations() {
        List<ReservationEnt> list = reservationRepository.pastLaneReservations(lane1.getUuid());
        assertTrue(list.contains(reservation1));
        assertFalse(list.contains(reservation2));
        assertFalse(list.contains(reservation3));

    }

    @Test
    void endReservation() throws ItemNotFound {
        LocalDateTime now = LocalDateTime.now();
        reservationRepository.endReservation(reservation3.getUuid(), now);
        assertEquals(now, reservationRepository.readById(reservation3.getUuid()).getEndReservation());
    }

    @Test
    void reservedLine() {
        assertTrue(reservationRepository.reservedLine(lane1.getUuid(), start1, end3));
        assertTrue(reservationRepository.reservedLine(lane1.getUuid(), start2, end3));
        assertFalse(reservationRepository.reservedLine(lane1.getUuid(), start2, start2));

        assertFalse(reservationRepository.reservedLine(lane2.getUuid(), start1, end1));
        assertTrue(reservationRepository.reservedLine(lane2.getUuid(), start2, end2));
        assertFalse(reservationRepository.reservedLine(lane2.getUuid(), start3, end3));
    }
}