package adapters;


import Port.In.CreateLanePort;
import Port.In.DeleteLanePort;
import Port.In.UpdateLanePort;
import exceptions.ItemNotFound;
import model.Lane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.LaneRepository;

import java.util.UUID;

import static converters.LaneConverter.convertFromLane;
import static converters.LaneConverter.convertToLane;

@Component
public class LanePortInAdapter implements CreateLanePort, DeleteLanePort, UpdateLanePort {

    @Autowired
    LaneRepository laneRepository;

    @Override
    public Lane create(Lane lane) {
        return convertToLane(laneRepository.create(convertFromLane(lane)));
    }

    @Override
    public Lane delete(UUID uuid) throws ItemNotFound {
        return convertToLane(laneRepository.delete(uuid));
    }

    @Override
    public Lane update(Lane lane) throws ItemNotFound {
        return convertToLane(laneRepository.update(convertFromLane(lane)));
    }
}
