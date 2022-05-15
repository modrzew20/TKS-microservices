package modelEnt;

import java.util.UUID;

public class AdministratorEnt extends UserEnt {

    public AdministratorEnt(UUID uuid, String login, String password, Boolean isActive) {
        super(uuid, login, password, isActive);
    }

    @Override
    public AccessLevelEnt getAccessLevel() {
        return AccessLevelEnt.Administrator;
    }
}
