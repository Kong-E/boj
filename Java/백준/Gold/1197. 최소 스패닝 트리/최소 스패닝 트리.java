import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        // Union-Find 배열 초기화
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;

        // 간선 정보 입력받기
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(u, v, weight));
        }

        int mstWeight = 0;
        int count = 0;

        // 크루스칼 알고리즘 실행
        while (!pq.isEmpty() && count < V - 1) {
            Edge edge = pq.poll();
            if (union(edge.u, edge.v)) {
                mstWeight += edge.weight;
                count++;
            }
        }

        System.out.println(mstWeight);
    }

    // Union-Find: Find 연산 (경로 압축)
    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    // Union-Find: Union 연산
    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootB] = rootA;
        return true;
    }

    // 간선 클래스 (Comparable 구현)
    static class Edge implements Comparable<Edge> {
        int u, v, weight;
        Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}
