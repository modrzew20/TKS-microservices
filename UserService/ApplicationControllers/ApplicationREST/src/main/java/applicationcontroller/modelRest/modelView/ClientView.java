package applicationcontroller.modelRest.modelView;

import java.util.UUID;

public class ClientView extends UserView {
    public ClientView(UUID uuid, String login, String password, Boolean isActive) {
        super(uuid, login, password, isActive);
    }

    @Override
    public AccessLevelView getAccessLevel() {
        return AccessLevelView.Client;
    }
}
