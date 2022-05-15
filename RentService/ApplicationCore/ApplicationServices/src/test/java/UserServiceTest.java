//
//
//import exceptions.LoginInUseException;
//import model.AccessLevel;
//import model.User;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import service.UserService;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//public class UserServiceTest {
//    UserService userService;
//
//    @BeforeEach
//    void init() throws LoginInUseException {
////        userService = new UserService();
//        userService.addUser(AccessLevel.Client,"testimie","testhaslo");
//    }
//
//    @Test
//    void addUserTest() throws LoginInUseException {
//        int size = userService.readAllUser().size();
//        userService.addUser(AccessLevel.Client,"testimie1","testhaslo1");
//        assertEquals(size+1,userService.readAllUser().size());
//    }
//
//    @Test
//    void updateUserTest() throws LoginInUseException {
//        User user = userService.readAllUser().get(0);
//        userService.updateUser(user.getUuid(),"testimie2","testhaslo2");
//        assertEquals(userService.readOneUser(user.getUuid()).getLogin(),"testimie2");
//        assertEquals(userService.readOneUser(user.getUuid()).getPassword(),"testhaslo2");
//    }
//
//    @Test
//    void activeUserTest() throws LoginInUseException {
//        User user = userService.readAllUser().get(0);
//        userService.deactivateUser(user.getUuid());
//        assertEquals(userService.readOneUser(user.getUuid()).getActive(),false);
//        userService.activateUser(user.getUuid());
//        assertEquals(userService.readOneUser(user.getUuid()).getActive(),true);
//
//    }
//
//
//}