package service;


import Port.In.CreateUserPort;
import Port.In.DeleteUserPort;
import Port.In.UpdateUserPort;
import Port.Out.ReadUserPort;
import exceptions.CannotDeleteItem;
import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import kafka.dto.UserDto;
import kafka.message.Action;
import kafka.message.Message;
import kafka.producer.Sender;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Value("${spring.kafka.topic.userTopic:USER_TOPIC}")
    private String USER_TOPIC;

    @Value("${spring.kafka.topic.userResponseQueue:USER_RESPONSE_QUEUE}")
    private String USER_RESPONSE_QUEUE;

    private Sender sender;

    private final Object lock = new Object();
    private final ReadUserPort readUserPort;
    private final CreateUserPort createUserPort;
    private final DeleteUserPort deleteUserPort;
    private final UpdateUserPort updateUserPort;

    @Autowired
    public UserService(Sender sender, ReadUserPort readUserPort, CreateUserPort createUserPort, DeleteUserPort deleteUserPort, UpdateUserPort updateUserPort) {
        this.sender = sender;
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

    public User addUser(AccessLevel accessLevel, String login, String password) throws LoginInUseException {
    User createdUser = null;
        try {
            synchronized (lock) {
                switch (accessLevel) {
                    case Administrator -> {
                        createdUser = createUserPort.create(new Administrator(UUID.randomUUID(), login, password, true));
                    }
                    case ResourceAdministrator -> {
                        createdUser = createUserPort.create(new ResourceAdministrator(UUID.randomUUID(), login, password, true));
                    }
                    case Client -> {
                        createdUser = createUserPort.create(new Client(UUID.randomUUID(), login, password, true));
                    }
                    default -> {
                        return null;
                    }
                }
            }
            return createdUser;
        } finally {
            if (createdUser != null) {
                Message message = new Message(Action.CREATE, new UserDto(createdUser));
                sender.send(USER_TOPIC, message);
            }
        }
    }

    public User updateUser(UUID uuid, String login, String password) throws
            LoginInUseException, ItemNotFound {
        User updatedUser = null;
        try {
            synchronized (lock) {
                User user = readUserPort.readById(uuid);
                switch (user.getAccessLevel()) {
                    case Administrator -> {
                        updatedUser = updateUserPort.update(new Administrator(uuid, login, password, user.getActive()));
                    }
                    case ResourceAdministrator -> {
                        updatedUser = updateUserPort.update(new ResourceAdministrator(uuid, login, password, user.getActive()));
                    }
                    case Client -> {
                        updatedUser = updateUserPort.update(new Client(uuid, login, password, user.getActive()));
                    }
                    default -> {
                        return null;
                    }
                }
            }
            return updatedUser;
        } finally {
            if (updatedUser != null) {
                Message message = new Message(Action.UPDATE, new UserDto(updatedUser));
                sender.send(USER_TOPIC, message);
            }
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


    public User deactivateUser(UUID uuid) throws ItemNotFound {
        User editedUser = null;
        try {
            synchronized (lock) {
                editedUser = updateUserPort.deactivate(uuid);
            }
            return editedUser;
        } finally {
            if (editedUser != null) {
                Message message = new Message(Action.UPDATE, new UserDto(editedUser));
                sender.send(USER_TOPIC, message);
            }
        }
    }

    public User activateUser(UUID uuid) throws ItemNotFound {
        User editedUser = null;
        try {
            synchronized (lock) {
                editedUser = updateUserPort.activate(uuid);
            }
            return editedUser;
        } finally {
            if (editedUser != null) {
                Message message = new Message(Action.UPDATE, new UserDto(editedUser));
                sender.send(USER_TOPIC, message);
            }
        }
    }


    public User deleteUser(UUID uuid) throws ItemNotFound {
        synchronized (lock) {
            return deleteUserPort.delete(uuid);
        }
    }
}
