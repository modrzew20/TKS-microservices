package converters;

import model.*;
import modelEnt.UserEnt;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static User convertToUser(UserEnt userEnt) {
        // anonymous class returned
        return new User(userEnt.getUuid(), userEnt.getLogin()) {
            @Override
            public AccessLevel getAccessLevel() {
                return null;
            }
        };
    }

    public static List<User> convertToListUser(List<UserEnt> userEntList) {
        List<User> convertedList = new ArrayList<>();
        for (UserEnt user : userEntList) {
            convertedList.add(convertToUser(user));
        }
        return convertedList;
    }


    public static UserEnt convertFromUser(User user) {
        return new UserEnt(user.getUuid(), user.getLogin(), user.getActive());
    }

    public static List<UserEnt> convertFromListUser(List<User> userList) {
        return userList.stream().map(UserConverter::convertFromUser).toList();
    }
}
