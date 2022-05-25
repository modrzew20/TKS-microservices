package applicationcontroller.converters;

import applicationcontroller.modelRest.modelView.ReservationView;
import model.Reservation;


public class ReservationViewConverter {

    public static Reservation convertToReservation(ReservationView reservationView) {
        if (reservationView == null) return null;
        return new Reservation(reservationView.getUuid(), LaneViewConverter.convertToLane(reservationView.getLane()),
                reservationView.getUser(),
                reservationView.getStartReservation(), reservationView.getEndReservation());
    }

    public static ReservationView convertFromReservation(Reservation reservation) {
        if (reservation == null) return null;
        return new ReservationView(reservation.getUuid(), LaneViewConverter.convertFromLane(reservation.getLane()),
                reservation.getUser(),
                reservation.getStartReservation(), reservation.getEndReservation());
    }
}
