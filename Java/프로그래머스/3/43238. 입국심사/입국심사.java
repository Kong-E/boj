import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long left = 0;
        long right = (long)times[times.length - 1] * n;
        
        while (left <= right) {
            long mid = (left + right) / 2; // 시간
            
            long sum = 0; // 심사 가능한 사람 수
            for (int time : times) {
                sum += mid / time;
                if (sum >= n) break;
            }
            
            if (sum >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}