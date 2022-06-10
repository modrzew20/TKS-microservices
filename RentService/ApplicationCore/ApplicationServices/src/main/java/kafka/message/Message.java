package kafka.message;

import kafka.dto.UserDto;

public class Message {
    private Action action;
    private UserDto userDto;

    public Message() {
    }

    public Message(Action action, UserDto userDto) {
        this.action = action;
        this.userDto = userDto;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    @Override
    public String toString() {
        return "Message{" +
                "action=" + action +
                ", userDto=" + userDto +
                '}';
    }
}
