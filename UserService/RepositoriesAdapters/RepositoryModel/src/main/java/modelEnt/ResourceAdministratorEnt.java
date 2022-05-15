package modelEnt;

import java.util.UUID;

public class ResourceAdministratorEnt extends UserEnt {
    public ResourceAdministratorEnt(UUID uuid, String login, String password, Boolean isActive) {
        super(uuid, login, password, isActive);
    }

    @Override
    public AccessLevelEnt getAccessLevel() {
        return AccessLevelEnt.ResourceAdministrator;
    }
}
