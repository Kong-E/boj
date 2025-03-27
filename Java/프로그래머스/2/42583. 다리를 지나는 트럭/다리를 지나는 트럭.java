import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        // truck_weights에서 for문 돌리며, 다리에 올라갈 수 있는지 확인
        // 1. 올라갈 수 없으면 
        // -> 큐에 있는 것들이 다 건너가기를 기다리기, 카운트
        // 2. 올라갈 수 있으면
        // -> 큐에 추가
        Queue<Integer> q = new LinkedList<>();
        
        int totalW = 0;
        int second = 0;
        int idx = 0;
        do {
            if (idx == truck_weights.length) break;
            if (q.size() == bridge_length) {
                totalW -= q.poll();
            }
            int w = truck_weights[idx];
            if (totalW + w > weight) {
                q.offer(0);
            } else {
                q.offer(w);
                totalW += w;
                idx++;
            }
            second += 1;
        } while (!q.isEmpty());

        return second + bridge_length;
    }
}