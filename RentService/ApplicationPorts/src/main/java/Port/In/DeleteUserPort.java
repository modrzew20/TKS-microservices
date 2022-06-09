package Port.In;

import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import model.User;

import java.util.UUID;

public interface DeleteUserPort {
    User delete(UUID uuid) throws CannotDeleteItem, ItemNotFound;

    User deleteLocalObject(String login);
}