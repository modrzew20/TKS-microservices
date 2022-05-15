package model;

import java.util.UUID;

public class ResourceAdministrator extends User {
    public ResourceAdministrator(UUID uuid, String login, String password, Boolean isActive) {
        super(uuid, login, password, isActive);
    }

    @Override
    public AccessLevel getAccessLevel() {
        return AccessLevel.ResourceAdministrator;
    }
}
