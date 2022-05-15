package applicationcontroller.api;

import applicationcontroller.adapters.LaneServiceAdapters;
import applicationcontroller.modelRest.modelView.LANE_TYPE_View;
import applicationcontroller.modelRest.modelView.LaneView;
import exceptions.ItemNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/lane")
public class LaneController {

    private final LaneServiceAdapters laneServiceAdapters;

    @Autowired
    public LaneController(LaneServiceAdapters laneServiceAdapters) {
        this.laneServiceAdapters = laneServiceAdapters;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LaneView> readAllLane() {
        return laneServiceAdapters.readAllLane();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/filter/{type}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LaneView> filterLines(@PathVariable() @NotBlank @Pattern(regexp = "vip|normal|premium") String type) {
        return laneServiceAdapters.readAllLane().stream().filter(lane -> lane.getType().equals(LANE_TYPE_View.valueOf(type))).collect(Collectors.toList());
    }


    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LaneView readLane(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        try {
            return laneServiceAdapters.readOneLane(UUID.fromString(uuid));
        } catch (ItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    //@Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}")
    //@Pattern(regexp = "vip|normal|premium")
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public LaneView addLine(@RequestParam("type") @NotBlank @Pattern(regexp = "vip|normal|premium") String type) {
        return laneServiceAdapters.addLane(type);
    }


    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public LaneView updateLine(@RequestParam("id") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid, @RequestParam @NotBlank @Pattern(regexp = "vip|normal|premium") String type) {
        try {
            return laneServiceAdapters.updateLane(UUID.fromString(uuid), type);
        } catch (ItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public LaneView deleteLane(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        try {
            return laneServiceAdapters.deleteLine(UUID.fromString(uuid));
        } catch (ItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
