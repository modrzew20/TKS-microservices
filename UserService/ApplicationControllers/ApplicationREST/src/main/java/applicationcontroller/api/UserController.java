package applicationcontroller.api;

import applicationcontroller.adapters.UserServiceAdapters;
import applicationcontroller.modelRest.modelView.UserView;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import model.AccessLevel;
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
    public UserView addUser(@RequestParam("accessLevel") @NotBlank @Pattern(regexp = "Administrator|Client|ResourceAdministrator", message = "Field accessLevel must be Administrator|Client|ResourceAdministrator.") String accessLevel,
                            @RequestParam("login") @NotBlank String login,
                            @RequestParam("password") @NotBlank String password) {
        try {
            return userServiceAdapters.addUser(AccessLevel.valueOf(accessLevel), login, password);
        } catch (LoginInUseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserView updateUser(@RequestParam("id") @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid,
                               @RequestParam("login") String login,
                               @RequestParam("password") String password) {

        try {
            return userServiceAdapters.updateUser(UUID.fromString(uuid), login, password);
        } catch (LoginInUseException | ItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping(value = "/readMany/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserView> readManyUsers(@PathVariable("login") @NotBlank String login) {
        return userServiceAdapters.readManyUser(login);
    }

    @PostMapping(value = "/deactivate/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserView deactivateUser(@PathVariable @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        try {
            return userServiceAdapters.deactivateUser(UUID.fromString(uuid));
        } catch (ItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping(value = "/activate/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserView activateUser(@PathVariable @NotBlank @Pattern(regexp =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}") String uuid) {
        try {
            return userServiceAdapters.activateUser(UUID.fromString(uuid));
        } catch (ItemNotFound e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
