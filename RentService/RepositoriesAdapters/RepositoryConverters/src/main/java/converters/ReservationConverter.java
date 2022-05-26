package converters;

import model.Reservation;
import modelEnt.ReservationEnt;

import java.util.List;

import static converters.LaneConverter.convertFromLane;
import static converters.LaneConverter.convertToLane;

public class ReservationConverter {

    public static Reservation convertToReservation(ReservationEnt reservationEnt) {
        if (reservationEnt == null) return null;
        return new Reservation(reservationEnt.getUuid(), convertToLane(reservationEnt.getLane()),
                reservationEnt.getClient(),
                reservationEnt.getStartReservation(), reservationEnt.getEndReservation());
    }

    public static List<Reservation> convertToListReservation(List<ReservationEnt> reservationEntList) {
        if (reservationEntList == null) return null;
        return reservationEntList.stream().map(ReservationConverter::convertToReservation).toList();
    }

    public static ReservationEnt convertFromReservation(Reservation reservation) {
        if (reservation == null) return null;
        return new ReservationEnt(reservation.getUuid(), convertFromLane(reservation.getLane()),
                reservation.getUser(),
                reservation.getStartReservation(), reservation.getEndReservation());
    }

    public static List<ReservationEnt> convertFromListReservation(List<Reservation> reservationList) {
        if (reservationList == null) return null;
        return reservationList.stream().map(ReservationConverter::convertFromReservation).toList();
    }

}
