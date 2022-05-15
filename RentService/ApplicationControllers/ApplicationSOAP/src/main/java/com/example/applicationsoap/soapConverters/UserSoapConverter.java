package com.example.applicationsoap.soapConverters;

import com.example.applicationsoap.soapmodel.usermodel.AccessLevelType;
import com.example.applicationsoap.soapmodel.usermodel.UserSoap;
import model.AccessLevel;
import model.User;


public class UserSoapConverter {

    public static UserSoap convertFromUser(User user) {
        if(user == null) return null;
        UserSoap userSoap = new UserSoap();
        switch (user.getAccessLevel()) {
            case Administrator -> userSoap.setAccessLevel(AccessLevelType.ADMINISTRATOR);
            case Client -> userSoap.setAccessLevel(AccessLevelType.CLIENT);
            case ResourceAdministrator -> userSoap.setAccessLevel(AccessLevelType.RESOURCE_ADMINISTRATOR);
        }
        userSoap.setLogin(user.getLogin());
        userSoap.setPassword(user.getPassword());
        userSoap.setUuid(user.getUuid().toString());
        userSoap.setIsActive(user.getActive());
        return userSoap;
    }

    public static AccessLevel convertToAccessLevel (AccessLevelType accessLevelType){
        switch (accessLevelType) {
            case ADMINISTRATOR -> {
                return AccessLevel.Administrator;
            }
            case CLIENT -> {
                return AccessLevel.Client;
            }
            case RESOURCE_ADMINISTRATOR -> {
                return AccessLevel.ResourceAdministrator;
            }
            default -> {return null;}
        }
    }






}
