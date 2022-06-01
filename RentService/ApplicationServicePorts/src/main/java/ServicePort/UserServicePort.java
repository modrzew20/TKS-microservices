package ServicePort;

import exceptions.ItemNotFound;
import exceptions.LoginInUseException;

import java.util.List;
import java.util.UUID;

public interface UserServicePort<T> {
    List<T> readAllUser();

    T addUser(String login) throws LoginInUseException;

    T updateUser(UUID uuid, String login) throws
            LoginInUseException, ItemNotFound;

    T readOneUser(UUID uuid) throws ItemNotFound;

    List<T> readManyUser(String login);

}