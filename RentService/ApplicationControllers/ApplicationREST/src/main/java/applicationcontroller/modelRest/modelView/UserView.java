package applicationcontroller.modelRest.modelView;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserView {

    private UUID uuid;
    private String login;
}
