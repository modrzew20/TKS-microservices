package converters;

import model.Lane;
import modelEnt.LaneEnt;

import java.util.List;

import static converters.LaneTypeConverter.convertFromType;
import static converters.LaneTypeConverter.convertToType;

public class LaneConverter {


    public static Lane convertToLane(LaneEnt laneEnt) {
        if (laneEnt == null) return null;
        return new Lane(laneEnt.getUuid(), convertToType(laneEnt.getType()));
    }

    public static List<Lane> convertToLaneList(List<LaneEnt> laneEntList) {
        if (laneEntList == null) return null;
        return laneEntList.stream().map(LaneConverter::convertToLane).toList();
    }

    public static LaneEnt convertFromLane(Lane lane) {
        if (lane == null) return null;
        return new LaneEnt(lane.getUuid(), convertFromType(lane.getType()));
    }

    public static List<LaneEnt> convertFromLaneList(List<Lane> laneList) {
        if (laneList == null) return null;
        return laneList.stream().map(LaneConverter::convertFromLane).toList();
    }
}
