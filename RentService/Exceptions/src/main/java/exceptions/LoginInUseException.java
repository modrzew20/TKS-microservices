package exceptions;

public class LoginInUseException extends ApplicationException {

    public LoginInUseException() {
        super();
    }

    public LoginInUseException(String message) {
        super(message);
    }
}