import java.io.*;
import java.util.*;

public class Main {
    static int INF = Integer.MAX_VALUE;

    static class Node implements Comparable<Node> {
        int vertex;
        int dist;

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node other) {
            return this.dist - other.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int c = 0; c < T; c++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 교차로 개수
            int m = Integer.parseInt(st.nextToken()); // 도로 개수
            int t = Integer.parseInt(st.nextToken()); // 목적지 후보 개수

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 출발지
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            List<List<Node>> graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            int ghDistance = 0;

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if ((a == g && b == h) || (a == h && b == g)) ghDistance = d;

                graph.get(a).add(new Node(b, d));
                graph.get(b).add(new Node(a, d));
            }

            int[] destination = new int[t];
            for (int i = 0; i < t; i++) {
                destination[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(destination);

            // 최단거리를 가는 동안 g와 h를 거쳤는지가 핵심
            int[] distFromS =  dijkstra(graph, new int[n + 1], s);
            int[] distFromG = dijkstra(graph, new int[n + 1], g);
            int[] distFromH = dijkstra(graph, new int[n + 1], h);

            for (int dest : destination) {
                int distGH = distFromS[g] + ghDistance + distFromH[dest];
                int distHG = distFromS[h] + ghDistance + distFromG[dest];
                if (distFromS[dest] == distGH || distFromS[dest] == distHG) {
                    sb.append(dest).append(" ");
                }
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int[] dijkstra(List<List<Node>> graph, int[] dist, int start) {
        Arrays.fill(dist, INF);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if (dist[curNode.vertex] < curNode.dist) continue;

            for (Node adjNode : graph.get(curNode.vertex)) {
                int newDist = curNode.dist + adjNode.dist;
                if (dist[adjNode.vertex] > newDist) {
                    dist[adjNode.vertex] = newDist;

                    pq.offer(new Node(adjNode.vertex, newDist));
                }
            }
        }

        return dist;
    }
}
