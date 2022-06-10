package kafka.message;

public enum Action {
    CREATE,
    UPDATE,
    DELETE,


    FAILED_TO_CREATE,
    FAILED_TO_UPDATE,
    FAILED_TO_DELETE,


    CREATE_SUCCESS,
    UPDATE_SUCCESS,
    DELETE_SUCCESS;

    public static Action getSuccessAction(Action action) {
        switch (action) {
            case CREATE -> {
                return CREATE_SUCCESS;
            }
            case UPDATE -> {
                return UPDATE_SUCCESS;
            }
            case DELETE -> {
                return DELETE_SUCCESS;
            }
        }
        return null;
    }

    public static Action getFailureAction(Action action) {
        switch (action) {
            case CREATE -> {
                return FAILED_TO_CREATE;
            }
            case UPDATE -> {
                return FAILED_TO_UPDATE;
            }
            case DELETE -> {
                return FAILED_TO_DELETE;
            }
        }
        return null;
    }
}
