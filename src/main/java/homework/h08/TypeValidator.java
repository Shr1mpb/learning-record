package homework.h08;

public class TypeValidator {
    public void validate(Object abc) {
        if (!(abc instanceof String)) {
            throw new RuntimeException("");
        }

    }
}
