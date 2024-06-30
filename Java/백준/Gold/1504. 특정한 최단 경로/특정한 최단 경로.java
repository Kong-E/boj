import java.io.*;
import java.util.*;

public class Main {
    // 1 -> v1 -> v2 -> N = 1에서 v1으로 가는 최단 경로 + v1에서 v2로 가는 최단 경로 + v2에서 N으로 가는 최단 경로
    // 1 -> v2 -> v1 -> N = 1에서 v2으로 가는 최단 경로 + v2에서 v1로 가는 최단 경로 + v1에서 N으로 가는 최단 경로
    // 둘 중에 더 작은 것
    static class Node {
        int vertex;
        int dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] dist1 = new int[N + 1]; // 노드 1에서 모든 노드까지의 최단 경로
        int[] distV1 = new int[N + 1]; // 노드 v1에서 모든 노드까지의 최단 경로
        int[] distV2 = new int[N + 1]; // 노드 v1에서 모든 노드까지의 최단 경로

        Arrays.fill(dist1, Integer.MAX_VALUE);
        Arrays.fill(distV1, Integer.MAX_VALUE);
        Arrays.fill(distV2, Integer.MAX_VALUE);

        dijkstra(dist1, graph, 1);
        dijkstra(distV1, graph, v1);
        dijkstra(distV2, graph, v2);

        int answer;
        if (dist1[v1] == Integer.MAX_VALUE || distV1[v2] == Integer.MAX_VALUE || distV2[N] == Integer.MAX_VALUE) {
            if (dist1[v2] == Integer.MAX_VALUE || distV2[v1] == Integer.MAX_VALUE || distV1[N] == Integer.MAX_VALUE) {
                answer = -1;
            } else {
                answer = dist1[v2] + distV2[v1] + distV1[N];
            }
        } else {
            if (dist1[v2] == Integer.MAX_VALUE || distV2[v1] == Integer.MAX_VALUE || distV1[N] == Integer.MAX_VALUE) {
                answer = dist1[v1] + distV1[v2] + distV2[N];
            } else {
                answer = Math.min(dist1[v1] + distV1[v2] + distV2[N], dist1[v2] + distV2[v1] + distV1[N]);
            }
        }
        System.out.println(answer);
    }

    static void dijkstra(int[] dist, ArrayList<ArrayList<Node>> graph, int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        pq.offer(new Node(startNode, 0));
        dist[startNode] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[curNode.vertex] < curNode.dist) continue;

            for (Node adjNode : graph.get(curNode.vertex)) {
                int newDist = curNode.dist + adjNode.dist;
                if (dist[adjNode.vertex] > newDist) {
                    dist[adjNode.vertex] = newDist;
                    pq.offer(new Node(adjNode.vertex, dist[adjNode.vertex]));
                }
            }
        }
    }
}
