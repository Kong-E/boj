import java.util.*;

class Solution {
    class Node {
        String word;
        int count = 0;
        
        Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int len = begin.length();
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[words.length];
        q.add(new Node(begin, 0));
        
        while (!q.isEmpty()) {
            Node pop = q.remove();
            
            if (pop.word.equals(target)) {
                answer = pop.count;
                break;
            }
            
            int count;
            for (int i = 0; i < words.length; i++) {
                if (!visited[i]) {
                    count = 0;
                    for (int j = 0; j < len; j++) {
                        if (pop.word.charAt(j) == words[i].charAt(j)) count++;
                    }
                    if (count == len - 1) {
                        q.add(new Node(words[i], pop.count + 1));
                        visited[i] = true;
                    }
                }
            }
        }
        return answer;
    }
}