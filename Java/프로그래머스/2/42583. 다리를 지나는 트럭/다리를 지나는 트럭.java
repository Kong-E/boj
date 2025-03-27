import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> q = new LinkedList<>();
        
        int totalW = 0;
        int second = 0;
        int idx = 0;
        do {
            if (q.size() == bridge_length) {
                totalW -= q.poll();
            }
            if (idx == truck_weights.length || totalW + truck_weights[idx] > weight) {
                q.offer(0);
            } else {
                q.offer(truck_weights[idx]);
                totalW += truck_weights[idx];
                idx++;
            }
            second += 1;
        } while (totalW != 0);

        return second;
    }
}