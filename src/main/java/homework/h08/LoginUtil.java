package homework.h08;

import java.util.Objects;

public class LoginUtil {
    public void login(String a, String a1) {
        if (!(Objects.equals(a, "a") && Objects.equals(a1, "a"))) {
            throw new InvalidUserException();
        }
    }
}
