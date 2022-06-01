package applicationcontroller.adapters;

import ServicePort.UserServicePort;
import applicationcontroller.converters.UserViewConverter;
import applicationcontroller.modelRest.modelView.UserView;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceAdapters implements UserServicePort<UserView> {

    @Autowired
    UserService userService;


    @Override
    public List<UserView> readAllUser() {
        return userService.readAllUser().stream().map(UserViewConverter::convertFromUser).toList();
    }

    @Override
    public UserView addUser(String login) throws LoginInUseException {
        return UserViewConverter.convertFromUser(userService.addUser(login));
    }

    @Override
    public UserView updateUser(UUID uuid, String login) throws LoginInUseException, ItemNotFound {
        return UserViewConverter.convertFromUser(userService.updateUser(uuid, login));
    }

    @Override
    public UserView readOneUser(UUID uuid) throws ItemNotFound {
        return UserViewConverter.convertFromUser(userService.readOneUser(uuid));
    }

    @Override
    public List<UserView> readManyUser(String login) {
        return userService.readManyUser(login).stream().map(UserViewConverter::convertFromUser).toList();
    }
}
