package applicationcontroller.api;

import applicationcontroller.adapters.UserServiceAdapters;
import applicationcontroller.modelRest.modelView.UserView;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceAdapters userServiceAdapters;

    @Autowired
    public UserController(UserServiceAdapters userService) {
        this.userServiceAdapters = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserView> readAllUser() {
        return userServiceAdapters.readAllUser();
    }


    @GetMapping(value = "/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserView readUser(@PathVariable("uuid") @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        try {
            return userServiceAdapters.readOneUser(UUID.fromString(uuid));
        } catch (ItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserView addUser(@RequestParam("login") @NotBlank String login) {
        try {
            return userServiceAdapters.addUser(login);
        } catch (LoginInUseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserView updateUser(@RequestParam("id") @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid,
                               @RequestParam("login") String login) {

        try {
            return userServiceAdapters.updateUser(UUID.fromString(uuid), login);
        } catch (LoginInUseException | ItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/readMany/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserView> readManyUsers(@PathVariable("login") @NotBlank String login) {
        return userServiceAdapters.readManyUser(login);
    }
}
