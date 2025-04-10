package LeetCodeHot;

import java.util.*;

public class LeetCodeHot69 {
    /**
     *  有效的括号
     *  给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
         * 左括号必须用相同类型的右括号闭合。
         * 左括号必须以正确的顺序闭合。
         * 每个右括号都有一个对应的相同类型的左括号。
     *
     */
    public boolean isValid(String s) {
        if (s.length() == 1) return false;
        HashMap<Character,Character> map = new HashMap();
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
        Deque<Character> q = new LinkedList<Character>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Character peek = q.peek();
            if(c.equals(map.get(peek))) {
                q.pop();
                continue;
            }
            q.push(c);
        }
        return q.isEmpty();
    }

    public boolean isValid2(String s) {
        Stack<Character> stack=new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch=='('||ch=='['||ch=='{'){
                stack.push(ch);
            }
            else if(!stack.isEmpty() &&ch==')'&&stack.peek()=='('){
                stack.pop();
            }
            else if(!stack.isEmpty() &&ch==']'&&stack.peek()=='['){
                stack.pop();
            }
            else if(!stack.isEmpty() &&ch=='}'&&stack.peek()=='{'){
                stack.pop();
            }
            else{
                return false;
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        LeetCodeHot69 leetcode = new LeetCodeHot69();
        leetcode.isValid("([])");
    }
}
