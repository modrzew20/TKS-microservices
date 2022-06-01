package repositoryTests;

import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import modelEnt.UserEnt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserRepositoryTest {

    UserRepository userRepository;

    @BeforeEach
    void beforeAll() throws LoginInUseException {
        userRepository = new UserRepository();
    }

    @Test
    void createUserTest() throws LoginInUseException {
        int size = userRepository.readAll().size();
        userRepository.create(new UserEnt(UUID.randomUUID(), "LOGINTEST"));
        assertEquals(userRepository.readAll().size(), size + 1);
    }

    @Test
    void failedCreateUserTest() throws LoginInUseException {
        userRepository.create(new UserEnt(UUID.randomUUID(), "LOGINTEST"));
        assertThrows(LoginInUseException.class, () -> userRepository.create(new UserEnt(UUID.randomUUID(), "LOGINTEST")));
        assertThrows(LoginInUseException.class, () -> userRepository.create(new UserEnt(UUID.randomUUID(), "")));
    }

    @Test
    void readByIdUserTest() throws LoginInUseException, ItemNotFound {
        UserEnt userEnt = new UserEnt(UUID.randomUUID(), "LOGINTEST");
        userRepository.create(userEnt);
        UserEnt result = userRepository.readById(userEnt.getUuid());
        assertEquals(result.getUuid(), userEnt.getUuid());
        assertEquals(result.getLogin(), userEnt.getLogin());
    }

    @Test
    void failedReadByIdUserTest() throws ItemNotFound {
        assertThrows(ItemNotFound.class, () -> userRepository.readById(UUID.randomUUID()));
    }

    @Test
    void updateUserTest() throws LoginInUseException, ItemNotFound {
        UserEnt userEnt = new UserEnt(UUID.randomUUID(), "LOGINTEST");
        userRepository.create(userEnt);
        String login = "LOGINUPDATED";
        userRepository.update(new UserEnt(userEnt.getUuid(), login));
        UserEnt result = userRepository.readById(userEnt.getUuid());
        assertEquals(result.getUuid(), userEnt.getUuid());
        assertEquals(result.getLogin(), login);
    }

    @Test
    void readManyUsersByLoginTest() throws LoginInUseException {
        int size = userRepository.readManyByLogin("1LOGIN").size();
        userRepository.create(new UserEnt(UUID.randomUUID(), "1LOGINTEST"));
        userRepository.create(new UserEnt(UUID.randomUUID(), "1LOGIN"));
        assertEquals(userRepository.readManyByLogin("1LOGIN").size(), size + 2);
    }

}
