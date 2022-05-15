package applicationcontroller.api;

import applicationcontroller.adapters.ReservationServiceAdapters;
import applicationcontroller.modelRest.modelView.ReservationView;
import exceptions.CannotCreateItem;
import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationServiceAdapters reservationServiceAdapters;

    @Autowired
    public ReservationController(ReservationServiceAdapters reservationService) {
        this.reservationServiceAdapters = reservationService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> readAllReservation() {
        return reservationServiceAdapters.readAllReservation();
    }


    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationView readOneReservation(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        try {
            return reservationServiceAdapters.readOneReservation(UUID.fromString(uuid));
        } catch (ItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationView addReservation(@RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String clientsUUID,
                                          @RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String laneUUID,
                                          @RequestParam @NotBlank @Pattern(regexp =
                                                  "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String start,
                                          @RequestParam @NotBlank @Pattern(regexp =
                                                  "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String end) {
        try {
            return reservationServiceAdapters.addReservation(UUID.fromString(clientsUUID), UUID.fromString(laneUUID), LocalDateTime.parse(start), LocalDateTime.parse(end));
        } catch (ItemNotFound | CannotCreateItem e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @GetMapping(value = "/pastClientReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> pastClientReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.pastClientReservations(UUID.fromString(uuid));
    }

    @GetMapping(value = "/presentClientReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> presentClientReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.presentClientReservations(UUID.fromString(uuid));
    }

    @GetMapping(value = "/pastLaneReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> pastLaneReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.pastLaneReservations(UUID.fromString(uuid));
    }

    @GetMapping(value = "/presentLaneReservations/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReservationView> presentLaneReservations(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.presentLaneReservations(UUID.fromString(uuid));
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationView updateReservation(@RequestParam @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid,
                                             @RequestParam @NotBlank @Pattern(regexp =
                                                     "[0-9]{4}-[0-9]{2}-[0-9]{2}T[0-9]{2}:[0-9]{2}:[0-9]{2}") String end) {
        return reservationServiceAdapters.endReservation(UUID.fromString(uuid), LocalDateTime.parse(end));
    }

    @PostMapping(value = "/end/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationView endReservation(@PathVariable("uuid") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        return reservationServiceAdapters.endReservation(UUID.fromString(uuid), LocalDateTime.now());

    }

    @DeleteMapping(value = "/delete/{param}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ReservationView deleteReservation(@PathVariable("param") @NotBlank @Pattern(regexp = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        try {
            return reservationServiceAdapters.delete(UUID.fromString(uuid));
        } catch (ItemNotFound | CannotDeleteItem e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
