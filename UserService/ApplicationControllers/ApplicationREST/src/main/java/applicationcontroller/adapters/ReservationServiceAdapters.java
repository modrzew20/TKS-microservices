package applicationcontroller.adapters;


import ServicePort.ReservationServicePort;
import applicationcontroller.converters.ReservationViewConverter;
import applicationcontroller.modelRest.modelView.ReservationView;
import exceptions.CannotCreateItem;
import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ReservationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationServiceAdapters implements ReservationServicePort<ReservationView> {

    @Autowired
    ReservationService reservationService;

    @Override
    public List<ReservationView> readAllReservation() {
        return reservationService.readAllReservation().stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public ReservationView addReservation(UUID clientsUUID, UUID laneUUID, LocalDateTime start, LocalDateTime end) throws ItemNotFound, CannotCreateItem {
        return ReservationViewConverter.convertFromReservation(reservationService.addReservation(clientsUUID, laneUUID, start, end));
    }

    @Override
    public ReservationView readOneReservation(UUID uuid) throws ItemNotFound {
        return ReservationViewConverter.convertFromReservation(reservationService.readOneReservation(uuid));
    }

    @Override
    public List<ReservationView> pastClientReservations(UUID clientsUUID) {
        return reservationService.pastClientReservations(clientsUUID).stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public List<ReservationView> presentClientReservations(UUID clientsUUID) {
        return reservationService.presentClientReservations(clientsUUID).stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public List<ReservationView> pastLaneReservations(UUID laneUUID) {
        return reservationService.pastLaneReservations(laneUUID).stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public List<ReservationView> presentLaneReservations(UUID laneUUID) {
        return reservationService.presentLaneReservations(laneUUID).stream().map(ReservationViewConverter::convertFromReservation).toList();
    }

    @Override
    public ReservationView endReservation(UUID uuid, LocalDateTime localDateTime) {
        return ReservationViewConverter.convertFromReservation(reservationService.endReservation(uuid, localDateTime));
    }

    @Override
    public ReservationView delete(UUID uuid) throws ItemNotFound, CannotDeleteItem {
        return ReservationViewConverter.convertFromReservation(reservationService.delete(uuid));
    }
}
