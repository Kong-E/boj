import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        StringBuilder sb = new StringBuilder();
        Map<Integer, Integer> map = new TreeMap<>();
        int length = speeds.length;
        int[] deploy = new int[length];
        int work;
        
        for (int i = 0; i < length; i++) {
            work = (int) Math.ceil((float) (100 - progresses[i]) / speeds[i]);
            deploy[i] = i > 0 ? Math.max(deploy[i - 1], work) : work;
            map.put(deploy[i], map.getOrDefault(deploy[i], 0) + 1);
        }
        
        int[] answer = new int[map.size()];
        int idx = 0;
        for (Integer key : map.keySet()) {
            answer[idx++] = map.get(key);
        }
        
        return answer;
    }
}