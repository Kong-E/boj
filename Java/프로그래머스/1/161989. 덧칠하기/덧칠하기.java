import java.io.*;
import java.util.*;

class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        int left = section[0]; // 윈도우 시작점
        int right = left + m - 1; // 윈도우 끝점
        int i = 0; // section의 인덱스
        while (i < section.length) {
            int current = section[i];
            
            // current가 윈도우 범위 내에 속하면
            if (current <= right) {
                i++;
            } else {
                answer++;
                
                left = current; // 속하지 않는 점을 윈도우의 시작점으로 지정
                right = left + m - 1;
                
                // 윈도우의 끝점이 영역을 넘어가면 조정
                if (right > n) {
                    int diff = right - n;

                    left -= diff;
                    right = left + m - 1;
                }
            }
        }
        
        return answer + 1;
    }
}