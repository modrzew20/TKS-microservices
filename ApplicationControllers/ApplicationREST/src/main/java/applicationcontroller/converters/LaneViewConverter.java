package applicationcontroller.converters;

import applicationcontroller.modelRest.modelView.LaneView;
import model.Lane;


public class LaneViewConverter {


    public static Lane convertToLane(LaneView laneView) {
        if (laneView == null) return null;
        return new Lane(laneView.getUuid(), LaneTypeViewConverter.convertToType(laneView.getType()));
    }

    public static LaneView convertFromLane(Lane lane) {
        if (lane == null) return null;
        return new LaneView(lane.getUuid(), LaneTypeViewConverter.convertFromType(lane.getType()));
    }
}
