package Port.In;

import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import model.User;

import java.util.UUID;

public interface UpdateUserPort {
    User update(User object) throws LoginInUseException, ItemNotFound;

    User activate(UUID uuid) throws ItemNotFound;

    User deactivate(UUID uuid) throws ItemNotFound;
}
