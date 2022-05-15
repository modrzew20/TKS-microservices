package model;

import java.util.UUID;

public class Administrator extends User {

    public Administrator(UUID uuid, String login, String password, Boolean isActive) {
        super(uuid, login, password, isActive);
    }

    @Override
    public AccessLevel getAccessLevel() {
        return AccessLevel.Administrator;
    }
}
