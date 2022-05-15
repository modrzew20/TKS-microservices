package applicationcontroller;

import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApplicationControllerApplication.class)
@Disabled("Disabled until UserService is fixed")
//TODO Remove after userService is updated
public interface SpringTest {
    String BASE_URL = "http://localhost:";
}
