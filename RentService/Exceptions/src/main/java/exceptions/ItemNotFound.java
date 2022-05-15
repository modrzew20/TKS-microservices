package exceptions;

public class ItemNotFound extends ApplicationException {

    public ItemNotFound() {
        super();
    }

    public ItemNotFound(String message) {
        super(message);
    }
}
