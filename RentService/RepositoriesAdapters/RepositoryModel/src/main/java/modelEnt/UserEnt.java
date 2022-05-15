package modelEnt;

import java.util.UUID;

public class UserEnt {

    private UUID uuid;
    private String login;
    private Boolean isActive;

    public UserEnt(UUID uuid, String login, Boolean isActive) {
        this.uuid = uuid;
        this.login = login;
        this.isActive = isActive;
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

}
