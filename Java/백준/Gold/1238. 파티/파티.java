import java.io.*;
import java.util.*;

public class Main {
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
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, dist));
        }

        int[][] startDist = new int[N + 1][N + 1]; // 각 정점에서 X로 가는 최단거리
        int[][] endDist = new int[N + 1][N + 1]; // X에서 출발하여 각 정점까지 도착하는 최단거리

        for (int i = 1; i <= N; i++) {
            Arrays.fill(startDist[i], Integer.MAX_VALUE);
            Arrays.fill(endDist[i], Integer.MAX_VALUE);
        }

        // 각 정점에서 X로 가기
        for (int i = 1; i <= N; i++) {
            dijkstra(startDist, graph, i);
        }

        // X에서 출발하여 각 정점까지의 최단거리 구하기
        dijkstra(endDist, graph, X);

        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            int sum = startDist[i][X] + endDist[X][i];
            if (max < sum) max = sum;
        }

        System.out.println(max);
    }

    static void dijkstra(int[][] dist, ArrayList<ArrayList<Node>> graph, int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.dist - o2.dist);
        pq.offer(new Node(startNode, 0));
        dist[startNode][startNode] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[startNode][curNode.vertex] < curNode.dist) continue;

            for (Node adjNode : graph.get(curNode.vertex)) {
                int newDist = curNode.dist + adjNode.dist;
                if (dist[startNode][adjNode.vertex] > newDist) {
                    dist[startNode][adjNode.vertex] = newDist;
                    pq.offer(new Node(adjNode.vertex, dist[startNode][adjNode.vertex]));
                }
            }
        }
    }
}
