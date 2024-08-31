import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        boolean[] checked = new boolean[section.length];
        
        int left = section[0];
        int right = left + m - 1;
        int i = 0;
        while (i < section.length) {
            int current = section[i];
            if (current <= right && current >= left) {
                checked[i] = true;
                i++;
            } else {
                answer++;
                left = current;
                right = left + m - 1;
                if (right > n) {
                    while (right > n) {
                        left--;
                        right = left + m - 1;
                    }
                }
            }
        }
        
        return answer + 1;
    }
}