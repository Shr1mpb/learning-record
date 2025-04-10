package homework.h08;

public class InvalidUserException extends RuntimeException {
    public InvalidUserException() {
    }

    public InvalidUserException(String message) {
        super(message);
    }
}
