package com.example.applicationsoap.soapAdapters;//package com.example.applicationsoapapi.soapAdapters;

import ServicePort.LaneServicePort;
import com.example.applicationsoap.soapConverters.LaneSoapConverter;
import com.example.applicationsoap.soapmodel.lanemodel.LaneSoap;
import exceptions.ItemNotFound;
import model.LANE_TYPE;
import model.Lane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LaneService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.applicationsoap.soapConverters.LaneSoapConverter.convertFromLane;


@Service
public class LaneServiceSoapAdapter implements LaneServicePort<LaneSoap> {

    @Autowired
    LaneService laneService;

    @Override
    public List<LaneSoap> readAllLane() {
        return laneService.readAllLane().stream().map(LaneSoapConverter :: convertFromLane).toList();
    }

    @Override
    public LaneSoap addLane(String lane_type) {
        return convertFromLane(laneService.addLane(lane_type));
    }

    @Override
    public LaneSoap updateLane(UUID uuid, String lane_type) throws ItemNotFound {
        return convertFromLane(laneService.updateLane(uuid,lane_type));
    }

    @Override
    public LaneSoap readOneLane(UUID uuid) throws ItemNotFound {
        return convertFromLane(laneService.readOneLane(uuid));
    }

    @Override
    public LaneSoap deleteLine(UUID uuid) throws ItemNotFound {
        return convertFromLane(laneService.deleteLine(uuid));
    }
}
