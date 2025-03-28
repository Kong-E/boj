import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        // 1. 우선 순위 큐에서 2개를 빼고
        // 2. 섞는다
        // 3. 다시 넣는다
        // 4. 다시 빼는데 첫번 째 요소가 K 이상이라면 멈춘다.
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int s : scoville) {
            pq.offer(s);
        }
        
        while (!pq.isEmpty()) {
            if (pq.peek() >= K) break;
            
            int first = pq.poll();
            
            if (!pq.isEmpty()) {
                int second = pq.poll();

                int newOne = first + (second * 2);

                pq.offer(newOne);

                answer++;
            } else {
                answer = -1;
            }
        }
        
        return answer;
    }
}

// 레오는 매운 것을 좋아해서 스코빌 지수를 k 이상으로 만든다
// 스코빌 지수가 가장 낮은 2개의 음식을 섞어 새로운 음식을 만든다
// 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)
// 모든 음식의 스코빌 지수가 K 이상이 될 때까지 반복
// 섞어야 하는 최소 횟수는?!