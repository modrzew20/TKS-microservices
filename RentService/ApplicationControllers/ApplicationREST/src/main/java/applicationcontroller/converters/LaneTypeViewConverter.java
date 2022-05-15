package applicationcontroller.converters;

import applicationcontroller.modelRest.modelView.LANE_TYPE_View;
import model.LANE_TYPE;


public class LaneTypeViewConverter {

    public static LANE_TYPE_View convertFromType(LANE_TYPE lane_type) {
        if (lane_type == null) return null;
        switch (lane_type) {
            case normal -> {
                return LANE_TYPE_View.normal;
            }
            case vip -> {
                return LANE_TYPE_View.vip;
            }
            case premium -> {
                return LANE_TYPE_View.premium;
            }
            default -> {
                return null;
            }
        }
    }

    public static LANE_TYPE convertToType(LANE_TYPE_View lane_type_view) {
        if (lane_type_view == null) return null;
        switch (lane_type_view) {
            case normal -> {
                return LANE_TYPE.normal;
            }
            case vip -> {
                return LANE_TYPE.vip;
            }
            case premium -> {
                return LANE_TYPE.premium;
            }
            default -> {
                return null;
            }
        }
    }
}
