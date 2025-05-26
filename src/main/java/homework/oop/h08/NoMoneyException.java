package homework.oop.h08;

public class NoMoneyException extends RuntimeException{
    public NoMoneyException(String message) {
        super(message);
    }

    public NoMoneyException() {
    }
}
