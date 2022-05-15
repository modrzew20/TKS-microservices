package exceptions;

public class CannotCreateItem extends ApplicationException {
    public CannotCreateItem() {
        super();
    }

    public CannotCreateItem(String message) {
        super(message);
    }
}
