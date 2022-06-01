package applicationcontroller.converters;

import applicationcontroller.modelRest.modelView.UserView;
import model.User;


public class UserViewConverter {

    public static User convertToUser(UserView userView) {
        if (userView == null) return null;
        return new User(userView.getUuid(), userView.getLogin());
    }


    public static UserView convertFromUser(User user) {
        if (user == null) return null;
        return new UserView(user.getUuid(), user.getLogin());

    }
}
