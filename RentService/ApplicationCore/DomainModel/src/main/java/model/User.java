package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
public class User {

    @Getter @Setter
    private UUID uuid;

    @Getter @Setter
    private String login;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUuid(), user.getUuid()) &&
                Objects.equals(getLogin(), user.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getLogin());
    }

}
