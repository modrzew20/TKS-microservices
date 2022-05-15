package model;

import java.util.UUID;

public class Client extends User {
    public Client(UUID uuid, String login, String password, Boolean isActive) {
        super(uuid, login, password, isActive);
    }

    @Override
    public AccessLevel getAccessLevel() {
        return AccessLevel.Client;
    }
}
