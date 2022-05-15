package applicationcontroller.modelRest.modelView;

import java.util.UUID;

public class ResourceAdministratorView extends UserView {
    public ResourceAdministratorView(UUID uuid, String login, String password, Boolean isActive) {
        super(uuid, login, password, isActive);
    }

    @Override
    public AccessLevelView getAccessLevel() {
        return AccessLevelView.ResourceAdministrator;
    }
}
