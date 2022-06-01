package service;


import Port.In.CreateUserPort;
import Port.In.DeleteUserPort;
import Port.In.UpdateUserPort;
import Port.Out.ReadUserPort;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

// TODO to bedzie wywolywac mikroserwis UserService
@Service
public class UserService {

    private final Object lock = new Object();
    private final ReadUserPort readUserPort;
    private final CreateUserPort createUserPort;
    private final DeleteUserPort deleteUserPort;
    private final UpdateUserPort updateUserPort;

    @Autowired
    public UserService(ReadUserPort readUserPort, CreateUserPort createUserPort, DeleteUserPort deleteUserPort, UpdateUserPort updateUserPort) {
        this.readUserPort = readUserPort;
        this.createUserPort = createUserPort;
        this.deleteUserPort = deleteUserPort;
        this.updateUserPort = updateUserPort;
    }

    public List<User> readAllUser() {
        synchronized (lock) {
            return readUserPort.readAll();
        }
    }

    public User addUser(String login) throws LoginInUseException {
        synchronized (lock) {
            return createUserPort.create(new User(UUID.randomUUID(), login));
        }
    }

    public User updateUser(UUID uuid, String login) throws
            LoginInUseException, ItemNotFound {
        synchronized (lock) {
            User user = readUserPort.readById(uuid);
            return updateUserPort.update(new User(user.getUuid(), login));
        }
    }


    public User readOneUser(UUID uuid) throws ItemNotFound {
        synchronized (lock) {
            return readUserPort.readById(uuid);
        }
    }

    public List<User> readManyUser(String login) {
        synchronized (lock) {
            return readUserPort.readManyByLogin(login);
        }
    }
}
