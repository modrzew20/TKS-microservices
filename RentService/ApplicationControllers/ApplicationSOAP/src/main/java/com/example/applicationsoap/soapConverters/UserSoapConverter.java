package com.example.applicationsoap.soapConverters;

import com.example.applicationsoap.soapmodel.usermodel.UserSoap;
import model.User;


public class UserSoapConverter {

    public static UserSoap convertFromUser(User user) {
        if(user == null) return null;
        UserSoap userSoap = new UserSoap();
        userSoap.setLogin(user.getLogin());
        userSoap.setUuid(user.getUuid().toString());
        return userSoap;
    }
}
