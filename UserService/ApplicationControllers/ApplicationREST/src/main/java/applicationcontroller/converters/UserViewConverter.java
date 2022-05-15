package applicationcontroller.converters;

import applicationcontroller.modelRest.modelView.AdministratorView;
import applicationcontroller.modelRest.modelView.ClientView;
import applicationcontroller.modelRest.modelView.ResourceAdministratorView;
import applicationcontroller.modelRest.modelView.UserView;
import model.Administrator;
import model.Client;
import model.ResourceAdministrator;
import model.User;


public class UserViewConverter {

    public static User convertToUser(UserView userView) {
        if (userView == null) return null;
        switch (userView.getAccessLevel()) {
            case Administrator -> {
                return new Administrator(userView.getUuid(), userView.getLogin(), userView.getPassword(), userView.getActive());
            }
            case ResourceAdministrator -> {
                return new ResourceAdministrator(userView.getUuid(), userView.getLogin(), userView.getPassword(), userView.getActive());
            }
            case Client -> {
                return new Client(userView.getUuid(), userView.getLogin(), userView.getPassword(), userView.getActive());
            }
            default -> {
                return null;
            }
        }
    }

    public static UserView convertFromUser(User user) {
        if (user == null) return null;
        switch (user.getAccessLevel()) {
            case Administrator -> {
                return new AdministratorView(user.getUuid(), user.getLogin(), user.getPassword(), user.getActive());
            }
            case ResourceAdministrator -> {
                return new ResourceAdministratorView(user.getUuid(), user.getLogin(), user.getPassword(), user.getActive());
            }
            case Client -> {
                return new ClientView(user.getUuid(), user.getLogin(), user.getPassword(), user.getActive());
            }
            default -> {
                return null;
            }
        }
    }
}
