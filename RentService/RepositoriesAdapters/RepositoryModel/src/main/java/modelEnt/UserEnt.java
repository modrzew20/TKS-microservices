package modelEnt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
public class UserEnt {

    @Getter
    @Setter
    private UUID uuid;
    @Getter @Setter
    private String login;
}
