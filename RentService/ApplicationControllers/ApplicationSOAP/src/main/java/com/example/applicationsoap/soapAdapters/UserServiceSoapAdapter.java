package com.example.applicationsoap.soapAdapters;

import ServicePort.UserServicePort;
import com.example.applicationsoap.soapConverters.UserSoapConverter;
import com.example.applicationsoap.soapmodel.usermodel.UserSoap;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import model.AccessLevel;
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
    public UserSoap addUser(AccessLevel accessLevel, String login, String password) throws LoginInUseException {
        return UserSoapConverter.convertFromUser(userService.addUser(accessLevel,login,password));
    }

    @Override
    public UserSoap updateUser(UUID uuid, String login, String password) throws LoginInUseException, ItemNotFound {
        return UserSoapConverter.convertFromUser(userService.updateUser(uuid,login,password));
    }

    @Override
    public UserSoap readOneUser(UUID uuid) throws ItemNotFound {
        return UserSoapConverter.convertFromUser(userService.readOneUser(uuid));
    }

    @Override
    public List<UserSoap> readManyUser(String login) {
        return userService.readManyUser(login).stream().map(UserSoapConverter::convertFromUser).toList();
    }

    @Override
    public UserSoap deactivateUser(UUID uuid) throws ItemNotFound {
        return UserSoapConverter.convertFromUser(userService.deactivateUser(uuid));
    }

    @Override
    public UserSoap activateUser(UUID uuid) throws ItemNotFound {
        return UserSoapConverter.convertFromUser(userService.activateUser(uuid));
    }
}
