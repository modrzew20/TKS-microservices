package repository;

import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import modelEnt.AdministratorEnt;
import modelEnt.ClientEnt;
import modelEnt.ResourceAdministratorEnt;
import modelEnt.UserEnt;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserRepository implements RepositoryInterface<UserEnt> {

    private final List<UserEnt> userList;

    public UserRepository() throws LoginInUseException {
        this.userList = new ArrayList<>();
        this.create(new AdministratorEnt(UUID.fromString("484e945c-9174-417a-b4e4-7736254ade4f"), "miciukaciu", "czesc", true));
        this.create(new ClientEnt(UUID.fromString("b2998c63-4621-443e-bf59-1e39e1f80170"), "pypensz", "czesc", true));
        this.create(new ResourceAdministratorEnt(UUID.fromString("6286cfa3-2993-44d3-aff4-a26ca9b2b75b"), "fici", "czesc", true));
    }

    private boolean loginExists(String login) {
        return userList.stream().anyMatch(user -> login.equals(user.getLogin()));
    }

    private static boolean canBeEdited(List<UserEnt> list, String login) {
        int count = (int) list.stream().filter(user -> login.equals(user.getLogin())).count();
        return count > 1;
    }

    private boolean checkIfExists(UUID uuid) {
        return userList.stream().anyMatch(user -> user.getUuid().equals(uuid));
    }

    @Override
    public List<UserEnt> readAll() {
        return userList;
    }

    @Override
    public UserEnt readById(UUID uuid) throws ItemNotFound {
        return userList.stream().filter(user -> uuid.equals(user.getUuid())).findFirst().orElseThrow(() -> new ItemNotFound("No user with UUID found"));
    }

    @Override
    public UserEnt create(UserEnt userEnt) throws LoginInUseException {
        if (Objects.equals(userEnt.getLogin(), "")) throw new LoginInUseException("Login cannot be empty");
        if (loginExists(userEnt.getLogin())) throw new LoginInUseException("This login is already in use.");
        if (userEnt.getUuid() == null || checkIfExists(userEnt.getUuid())) {
            UUID uuid = UUID.randomUUID();
            while (checkIfExists(uuid)) {
                uuid = UUID.randomUUID();
            }
            userEnt.setUuid(uuid);
        }
        userList.add(userEnt);
        return userEnt;
    }

    @Override
    public UserEnt delete(UUID uuid) throws CannotDeleteItem {
        throw new CannotDeleteItem();
    }

    @Override
    public UserEnt update(UserEnt object) throws LoginInUseException, ItemNotFound {
        UUID uuid = object.getUuid();
        UserEnt user = userList.stream().filter(u -> uuid.equals(u.getUuid())).findFirst().orElseThrow(() -> new ItemNotFound("No user with UUID found"));
        if (user != null) {
            if (loginExists(object.getLogin()))
                throw new LoginInUseException("This login is already in use.");
            if (object.getLogin() != null) {
                if (Objects.equals(object.getLogin(), "")) throw new LoginInUseException("Login cannot be empty");
                user.setLogin(object.getLogin());
            }
            if (object.getPassword() != null) user.setPassword(object.getPassword());
        }
        return user;
    }

    public List<UserEnt> readManyByLogin(String login) {
        Stream<UserEnt> stream = userList.stream().filter(user -> user.getLogin().contains(login));
        return stream.collect(Collectors.toList());

    }

    public UserEnt activate(UUID uuid) throws ItemNotFound {
        UserEnt user = this.readById(uuid);
        if (user != null) user.setActive(true);
        return user;
    }

    public UserEnt deactivate(UUID uuid) throws ItemNotFound {
        UserEnt user = this.readById(uuid);
        if (user != null) user.setActive(false);
        return user;
    }

}
