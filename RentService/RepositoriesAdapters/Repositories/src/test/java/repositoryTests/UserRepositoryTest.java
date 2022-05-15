package repositoryTests;

import exceptions.ItemNotFound;
import exceptions.LoginInUseException;
import modelEnt.AccessLevelEnt;
import modelEnt.ClientEnt;
import modelEnt.UserEnt;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    UserRepository userRepository;

    @BeforeEach
    void beforeAll() throws LoginInUseException {
        userRepository = new UserRepository();
    }

    @Test
    void createUserTest() throws LoginInUseException {
        int size = userRepository.readAll().size();
        userRepository.create(new ClientEnt(UUID.randomUUID(),"LOGINTEST","PASSWORDTEST",true));
        assertEquals(userRepository.readAll().size(),size+1);
    }

    @Test
    void failedCreateUserTest() throws LoginInUseException {
        userRepository.create(new ClientEnt(UUID.randomUUID(),"LOGINTEST","PASSWORDTEST",true));
        assertThrows(LoginInUseException.class,()->userRepository.create(new ClientEnt(UUID.randomUUID(),"LOGINTEST","PASSWORDTEST",true)));
        assertThrows(LoginInUseException.class,()->userRepository.create(new ClientEnt(UUID.randomUUID(),"","PASSWORDTEST",true)));
    }

    @Test
    void readByIdUserTest() throws LoginInUseException, ItemNotFound {
        ClientEnt clientEnt = new ClientEnt(UUID.randomUUID(),"LOGINTEST","PASSWORDTEST",true);
        userRepository.create(clientEnt);
        UserEnt result = userRepository.readById(clientEnt.getUuid());
        assertEquals(result.getAccessLevel(), AccessLevelEnt.Client);
        assertEquals(result.getUuid(),clientEnt.getUuid());
        assertEquals(result.getLogin(),clientEnt.getLogin());
        assertEquals(result.getPassword(),clientEnt.getPassword());
    }

    @Test
    void failedreadByIdUserTest() throws ItemNotFound {
        assertThrows(ItemNotFound.class , () ->userRepository.readById(UUID.randomUUID()));
    }

    @Test
    void updateUserTest() throws LoginInUseException, ItemNotFound {
        ClientEnt clientEnt = new ClientEnt(UUID.randomUUID(),"LOGINTEST","PASSWORDTEST",true);
        userRepository.create(clientEnt);
        String login = "LOGINUPDATED";
        String password = "PASSWORDUPDATE";
        userRepository.update(new ClientEnt(clientEnt.getUuid(),login,password,true));
        UserEnt result = userRepository.readById(clientEnt.getUuid());
        assertEquals(result.getAccessLevel(), AccessLevelEnt.Client);
        assertEquals(result.getUuid(),clientEnt.getUuid());
        assertEquals(result.getLogin(),login);
        assertEquals(result.getPassword(),password);
    }

    @Test
    void readManyUsersByLoginTest() throws LoginInUseException {
        int size = userRepository.readManyByLogin("1LOGIN").size();
        userRepository.create(new ClientEnt(UUID.randomUUID(),"1LOGINTEST","PASSWORDTEST",true));
        userRepository.create(new ClientEnt(UUID.randomUUID(),"1LOGIN","PASSWORDTEST",true));
        assertEquals(userRepository.readManyByLogin("1LOGIN").size(),size + 2);
    }

    @Test
    void activateTest() throws LoginInUseException, ItemNotFound {
        UUID uuid = UUID.randomUUID();
        userRepository.create(new ClientEnt(uuid,"LOGINTEST","PASSWORDTEST",false));
        userRepository.activate(uuid);
        assertEquals(userRepository.readById(uuid).getActive(),true);
    }

    @Test
    void deactiveTest() throws LoginInUseException, ItemNotFound{
        UUID uuid = UUID.randomUUID();
        userRepository.create(new ClientEnt(uuid,"LOGINTEST","PASSWORDTEST",true));
        userRepository.deactivate(uuid);
        assertEquals(userRepository.readById(uuid).getActive(),false);
    }
}
