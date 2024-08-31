class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        
        int left = section[0]; // 윈도우 시작점
        int right = left + m - 1; // 윈도우 끝점
        int i = 0; // section의 인덱스
        
        int current; int diff;
        while (i < section.length) {
            current = section[i];
            
            // current가 윈도우 범위 내에 속하면
            if (current <= right) {
                i++;
            } else {
                answer++;
                
                left = current;
                right = left + m - 1;
                
                if (right > n) {
                    diff = right - n;
                    
                    left -= diff;
                    right -= diff;
                }
            }
        }
        
        return answer + 1;
    }
}