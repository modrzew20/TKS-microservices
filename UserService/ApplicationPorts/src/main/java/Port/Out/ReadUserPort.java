package Port.Out;

import exceptions.ItemNotFound;
import model.User;

import java.util.List;
import java.util.UUID;

public interface ReadUserPort {

    List<User> readAll();

    User readById(UUID uuid) throws ItemNotFound;

    List<User> readManyByLogin(String login);
}
