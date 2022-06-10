package kafka.dto;

import model.User;

import java.util.UUID;

public class UserDto {
    private UUID uuid;
    private String login;

    public UserDto() {
    }

    public UserDto(User user) {
        this.uuid = user.getUuid();
        this.login = user.getLogin();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
