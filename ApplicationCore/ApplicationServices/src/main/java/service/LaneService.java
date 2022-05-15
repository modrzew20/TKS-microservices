package service;


import Port.In.CreateLanePort;
import Port.In.DeleteLanePort;
import Port.In.UpdateLanePort;
import Port.Out.LanesReservationPort;
import Port.Out.ReadLanePort;
import exceptions.ItemNotFound;
import model.LANE_TYPE;
import model.Lane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LaneService {

    private final Object lock = new Object();
    private final ReadLanePort readLanePort;
    private final CreateLanePort createLanePort;
    private final DeleteLanePort deleteLanePort;
    private final UpdateLanePort updateLanePort;
    private final LanesReservationPort reservationPortOut;

    @Autowired
    public LaneService(ReadLanePort readLanePort, CreateLanePort createLanePort, DeleteLanePort deleteLanePort, UpdateLanePort updateLanePort, LanesReservationPort reservationPortOut) {
        this.readLanePort = readLanePort;
        this.createLanePort = createLanePort;
        this.deleteLanePort = deleteLanePort;
        this.updateLanePort = updateLanePort;
        this.reservationPortOut = reservationPortOut;
    }

    public List<Lane> readAllLane() {
        synchronized (lock) {
            return readLanePort.readAll();
        }
    }

    public Lane addLane(String lane_type) {
        synchronized (lock) {
            return createLanePort.create(new Lane(null, LANE_TYPE.valueOf(lane_type)));
        }
    }

    public Lane updateLane(UUID uuid, String lane_type) throws ItemNotFound {
        if (reservationPortOut.presentLaneReservations(uuid).size() != 0) return null;
        synchronized (lock) {
            return updateLanePort.update(new Lane(uuid, LANE_TYPE.valueOf(lane_type)));
        }
    }

    public Lane readOneLane(UUID uuid) throws ItemNotFound {
        synchronized (lock) {
            return readLanePort.readById(uuid);
        }
    }

    public Lane deleteLine(UUID uuid) throws ItemNotFound {
        if (reservationPortOut.presentLaneReservations(uuid).size() != 0) return null;
        synchronized (lock) {
            return deleteLanePort.delete(uuid);
        }
    }
}


