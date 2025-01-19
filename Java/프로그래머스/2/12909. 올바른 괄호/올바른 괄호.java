import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = s.length() - 1; i >= 0; i--) {
            stack.push(s.charAt(i));
        }
        
        int count = 0;
        while (!stack.isEmpty()) {
            char pop = stack.pop();
            
            if (pop == '(') {
                count++;
            } else {
                if (count > 0) {
                    count--;
                } else {
                    return false;
                }
            }
        }
        
        System.out.println(stack);

        return count == 0;
    }
}