package modelEnt;

import java.util.UUID;

public class ClientEnt extends UserEnt {
    public ClientEnt(UUID uuid, String login, String password, Boolean isActive) {
        super(uuid, login, password, isActive);
    }

    @Override
    public AccessLevelEnt getAccessLevel() {
        return AccessLevelEnt.Client;
    }
}
