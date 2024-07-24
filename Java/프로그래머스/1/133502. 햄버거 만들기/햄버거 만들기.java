import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] ingredient) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int ing : ingredient) {
            stack.push(ing);
            
            int size = stack.size();
            if (size >= 4) {
                int one = stack.get(size - 4);
                int two = stack.get(size - 3);
                int three = stack.get(size -2);
                int four = stack.get(size -1);

                if (one == 1 && two == 2 && three == 3 && four == 1) {
                    answer++;
                    int count = 4;
                    while (count-- > 0) {
                        stack.pop();
                    }
                }
            }
        }
        
        return answer;
    }
}