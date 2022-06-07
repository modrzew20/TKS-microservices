package Port.In;

import exceptions.LoginInUseException;
import model.User;

public interface CreateUserPort {
    User create(User user) throws LoginInUseException;
}