package ServicePort;

import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import model.AccessLevel;

import java.util.List;
import java.util.UUID;

public interface UserServicePort<T> {
    List<T> readAllUser();

    T addUser(AccessLevel accessLevel, String login, String password) throws LoginInUseException;

    T updateUser(UUID uuid, String login, String password) throws
            LoginInUseException, ItemNotFound;

    T readOneUser(UUID uuid) throws ItemNotFound;

    List<T> readManyUser(String login);

    T deactivateUser(UUID uuid) throws ItemNotFound;

    T activateUser(UUID uuid) throws ItemNotFound;
}