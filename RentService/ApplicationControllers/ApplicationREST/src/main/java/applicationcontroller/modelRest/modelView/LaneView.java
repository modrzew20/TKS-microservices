package applicationcontroller.modelRest.modelView;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
@AllArgsConstructor
public class LaneView {

    private UUID uuid;
    private LANE_TYPE_View type;
}

