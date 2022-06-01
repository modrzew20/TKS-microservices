package applicationcontroller.modelRest.modelView;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
public class UserView {

    @Getter @Setter
    private UUID uuid;
    @Getter @Setter
    private String login;


}
