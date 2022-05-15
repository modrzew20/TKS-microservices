package com.example.applicationsoap.soapEndpoints;

import com.example.applicationsoap.soapAdapters.UserServiceSoapAdapter;
import com.example.applicationsoap.soapConverters.UserSoapConverter;
import com.example.applicationsoap.soapmodel.usermodel.*;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.UUID;

@Endpoint
public class UserEndpoint {
    private static final String URI = "http://example.com/applicationsoap/soapmodel/usermodel";

    @Autowired
    UserServiceSoapAdapter userServiceSoapAdapter;

    @PayloadRoot(namespace = URI, localPart = "ReadAllUserRequest")
    @ResponsePayload
    public ReadAllUserResponse readAllUserResponse(@RequestPayload ReadAllUserRequest request) {
        ReadAllUserResponse response = new ReadAllUserResponse();
        response.getUserSoap().addAll(userServiceSoapAdapter.readAllUser());
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "ReadOneUserRequest")
    @ResponsePayload
    public ReadOneUserResponse readOneUserResponse(@RequestPayload ReadOneUserRequest request) throws ItemNotFound {
        ReadOneUserResponse response = new ReadOneUserResponse();
        response.setUserSoap(userServiceSoapAdapter.readOneUser(UUID.fromString(request.getUuid())));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "ReadManyUserRequest")
    @ResponsePayload
    public ReadManyUserResponse readManyUserResponse(@RequestPayload ReadManyUserRequest request) {
        ReadManyUserResponse response = new ReadManyUserResponse();
        response.getUserSoap().addAll(userServiceSoapAdapter.readManyUser(request.getLogin()));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "CreateUserRequest")
    @ResponsePayload
    public CreateUserResponse createUserResponse(@RequestPayload CreateUserRequest request) throws LoginInUseException {
        CreateUserResponse response = new CreateUserResponse();
        response.setUserSoap(userServiceSoapAdapter.addUser(UserSoapConverter.convertToAccessLevel(request.getAccesslevel()),request.getLogin(),request.getPassword()));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "UpdateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUserResponse(@RequestPayload UpdateUserRequest request) throws LoginInUseException, ItemNotFound {
        UpdateUserResponse response = new UpdateUserResponse();
        response.setUserSoap(userServiceSoapAdapter.updateUser(UUID.fromString(request.getUuid()), request.getLogin(), request.getPassword()));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "DeactivateUserRequest")
    @ResponsePayload
    public DeactivateUserResponse deactivateUserResponse(@RequestPayload DeactivateUserRequest request) throws ItemNotFound {
        DeactivateUserResponse response = new DeactivateUserResponse();
        response.setUserSoap(userServiceSoapAdapter.deactivateUser(UUID.fromString(request.getUuid())));
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "ActivateUserRequest")
    @ResponsePayload
    public ActivateUserResponse activateUserResponse(@RequestPayload ActivateUserRequest request) throws ItemNotFound {
        ActivateUserResponse response = new ActivateUserResponse();
        response.setUserSoap(userServiceSoapAdapter.activateUser(UUID.fromString(request.getUuid())));
        return response;
    }

}
