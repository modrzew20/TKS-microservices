package converters;

import model.*;
import modelEnt.UserEnt;

import java.util.ArrayList;
import java.util.List;

public class UserConverter {

    public static User convertToUser(UserEnt userEnt) {
        return new User(userEnt.getUuid(), userEnt.getLogin());
    }

    public static List<User> convertToListUser(List<UserEnt> userEntList) {
        List<User> convertedList = new ArrayList<>();
        for (UserEnt user : userEntList) {
            convertedList.add(convertToUser(user));
        }
        return convertedList;
    }


    public static UserEnt convertFromUser(User user) {
        return new UserEnt(user.getUuid(), user.getLogin());
    }

    public static List<UserEnt> convertFromListUser(List<User> userList) {
        return userList.stream().map(UserConverter::convertFromUser).toList();
    }
}
