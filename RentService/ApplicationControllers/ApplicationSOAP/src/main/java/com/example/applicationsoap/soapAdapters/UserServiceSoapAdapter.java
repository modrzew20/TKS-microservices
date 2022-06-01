package com.example.applicationsoap.soapAdapters;

import ServicePort.UserServicePort;
import com.example.applicationsoap.soapConverters.UserSoapConverter;
import com.example.applicationsoap.soapmodel.usermodel.UserSoap;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceSoapAdapter implements UserServicePort<UserSoap> {

    @Autowired
    UserService userService;

    @Override
    public List<UserSoap> readAllUser() {
        return userService.readAllUser().stream().map(UserSoapConverter::convertFromUser).toList();
    }

    @Override
    public UserSoap addUser(String login) throws LoginInUseException {
        return UserSoapConverter.convertFromUser(userService.addUser(login));
    }

    @Override
    public UserSoap updateUser(UUID uuid, String login) throws LoginInUseException, ItemNotFound {
        return UserSoapConverter.convertFromUser(userService.updateUser(uuid,login));
    }

    @Override
    public UserSoap readOneUser(UUID uuid) throws ItemNotFound {
        return UserSoapConverter.convertFromUser(userService.readOneUser(uuid));
    }

    @Override
    public List<UserSoap> readManyUser(String login) {
        return userService.readManyUser(login).stream().map(UserSoapConverter::convertFromUser).toList();
    }
}
