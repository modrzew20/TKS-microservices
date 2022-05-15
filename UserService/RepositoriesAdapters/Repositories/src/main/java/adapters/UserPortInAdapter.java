package adapters;

import Port.In.CreateUserPort;
import Port.In.DeleteUserPort;
import Port.In.UpdateUserPort;
import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.UserRepository;

import java.util.UUID;

import static converters.UserConverter.convertFromUser;
import static converters.UserConverter.convertToUser;

@Component
public class UserPortInAdapter implements CreateUserPort, DeleteUserPort, UpdateUserPort {

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user) throws LoginInUseException {
        return convertToUser(userRepository.create(convertFromUser(user)));
    }

    @Override
    public User delete(UUID uuid) throws CannotDeleteItem {
        return convertToUser(userRepository.delete(uuid));
    }

    @Override
    public User update(User user) throws LoginInUseException, ItemNotFound {
        return convertToUser(userRepository.update(convertFromUser(user)));
    }

    @Override
    public User activate(UUID uuid) throws ItemNotFound {
        return convertToUser(userRepository.activate(uuid));
    }

    @Override
    public User deactivate(UUID uuid) throws ItemNotFound {
        return convertToUser(userRepository.deactivate(uuid));
    }
}
