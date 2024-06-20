import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int vertex;
        int dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dist = new int[n + 1];
        int[] predecessor = new int[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[curNode.vertex] < curNode.dist) continue;

            for (Node adjNode : graph.get(curNode.vertex)) {
                int newDist = curNode.dist + adjNode.dist;
                if (dist[adjNode.vertex] > newDist) {
                    dist[adjNode.vertex] = newDist;
                    predecessor[adjNode.vertex] = curNode.vertex;

                    pq.offer(new Node(adjNode.vertex, newDist));
                }
            }
        }

        Stack<Integer> seq = new Stack<>();
        seq.push(end);

        int curr = end;
        while (true) {
            int pre = predecessor[curr];
            seq.push(pre);
            if (pre == start) break;
            curr = pre;
        }

        sb.append(dist[end]).append("\n");
        sb.append(seq.size()).append("\n");
        while (!seq.isEmpty()) sb.append(seq.pop()).append(" ");

        System.out.print(sb);
    }
}
