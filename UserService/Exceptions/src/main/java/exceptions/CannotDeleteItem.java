package exceptions;

public class CannotDeleteItem extends ApplicationException {
    public CannotDeleteItem() {
        super();
    }

    public CannotDeleteItem(String message) {
        super(message);
    }
}
