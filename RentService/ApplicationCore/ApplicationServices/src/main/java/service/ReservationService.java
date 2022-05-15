package service;


import Port.In.CreateReservationPort;
import Port.In.DeleteReservationPort;
import Port.In.UpdateReservationPort;
import Port.Out.*;
import exceptions.ApplicationException;
import exceptions.CannotCreateItem;
import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import model.Lane;
import model.Reservation;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {

    private final Object lock = new Object();
    private final ReadReservationPort readReservationPort;
    private final ReadLanePort readLanePort;
    private final ReadUserPort readUserPort;
    private final CreateReservationPort createReservationPort;
    private final UpdateReservationPort updateReservationPort;
    private final DeleteReservationPort deleteReservationPort;
    private final ClientsReservationPort clientsReservationPort;
    private final LanesReservationPort lanesReservationPort;

    @Autowired
    public ReservationService(ReadReservationPort readReservationPort, ReadLanePort readLanePort, ReadUserPort readUserPort, CreateReservationPort createReservationPort, UpdateReservationPort updateReservationPort, DeleteReservationPort deleteReservationPort, ClientsReservationPort clientsReservationPort, LanesReservationPort lanesReservationPort) {
        this.readReservationPort = readReservationPort;
        this.readLanePort = readLanePort;
        this.readUserPort = readUserPort;
        this.createReservationPort = createReservationPort;
        this.updateReservationPort = updateReservationPort;
        this.deleteReservationPort = deleteReservationPort;
        this.clientsReservationPort = clientsReservationPort;
        this.lanesReservationPort = lanesReservationPort;
    }

    public List<Reservation> readAllReservation() {
        synchronized (lock) {
            return readReservationPort.readAll();
        }
    }

    public Reservation addReservation(UUID clientsUUID, UUID laneUUID, LocalDateTime start, LocalDateTime end) throws ItemNotFound, CannotCreateItem {

        Lane lane = readLanePort.readById(laneUUID);
        User client = readUserPort.readById(clientsUUID);
        if (!readUserPort.readById(clientsUUID).getActive() || createReservationPort.reservedLine(laneUUID, start, end))
            throw new CannotCreateItem("Cannot create reservation");
        synchronized (lock) {
            Reservation reservation = new Reservation(UUID.randomUUID(), lane, client, start, end);
            reservation = createReservationPort.create(reservation);
            return reservation;
        }
    }

    public Reservation readOneReservation(UUID uuid) throws ItemNotFound {
        synchronized (lock) {
            return readReservationPort.readById(uuid);
        }
    }

    public List<Reservation> pastClientReservations(UUID clientsUUID) {
        synchronized (lock) {
            return clientsReservationPort.pastClientReservations(clientsUUID);
        }
    }

    public List<Reservation> presentClientReservations(UUID clientsUUID) {
        synchronized (lock) {
            return clientsReservationPort.presentClientReservations(clientsUUID);
        }
    }

    public List<Reservation> pastLaneReservations(UUID laneUUID) {
        synchronized (lock) {
            return lanesReservationPort.pastLaneReservations(laneUUID);
        }
    }

    public List<Reservation> presentLaneReservations(UUID laneUUID) {
        synchronized (lock) {
            return lanesReservationPort.presentLaneReservations(laneUUID);
        }
    }

    public Reservation endReservation(UUID uuid, LocalDateTime localDateTime) {
        synchronized (lock) {
            return deleteReservationPort.endReservation(uuid, localDateTime);
        }
    }

    public Reservation delete(UUID uuid) throws ItemNotFound, CannotDeleteItem {
        synchronized (lock) {
            return deleteReservationPort.delete(uuid);
        }
    }

}
