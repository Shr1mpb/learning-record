package homework.h19.q04;

import java.util.LinkedHashSet;

public class StrUtil {
    public String removeDulpicatedChar(String str) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        char[] charArray = str.toCharArray();
        for (char c:charArray) {
            set.add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (Character c:set) {
            sb.append(c);
        }
        return sb.toString();
    }
}
