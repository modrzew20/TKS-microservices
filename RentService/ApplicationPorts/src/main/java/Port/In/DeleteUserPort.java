package Port.In;

import exceptions.CannotDeleteItem;
import model.User;

import java.util.UUID;

public interface DeleteUserPort {
    User delete(UUID uuid) throws CannotDeleteItem;
}