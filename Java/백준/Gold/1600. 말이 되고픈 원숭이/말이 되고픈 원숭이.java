import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[] cdx = {2, 2, -2, -2, 1, 1, -1, -1}; // 나이트의 x 방향 움직임
    static int[] cdy = {1, -1, 1, -1, 2, -2, 2, -2}; // 나이트의 y 방향 움직임

    static class Node {
        int x;
        int y;
        int count;
        int k;

        Node(int x, int y, int count, int k) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.k = k;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int k = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][] board = new int[h][w];
        boolean[][][] visited = new boolean[h][w][k+1];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 0, k));
        visited[0][0][k] = true;

        int dist = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Node curNode = q.poll();

            if (curNode.x == w - 1 && curNode.y == h - 1) {
                dist = curNode.count;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = curNode.x + dx[i];
                int ny = curNode.y + dy[i];

                if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                if (board[ny][nx] != 1 && !visited[ny][nx][curNode.k]) {
                    q.offer(new Node(nx, ny, curNode.count + 1, curNode.k));
                    visited[ny][nx][curNode.k] = true;
                }
            }
            if (curNode.k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = curNode.x + cdx[i];
                    int ny = curNode.y + cdy[i];

                    if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                    if (board[ny][nx] != 1 && !visited[ny][nx][curNode.k - 1]) {
                        q.offer(new Node(nx, ny, curNode.count + 1, curNode.k - 1));
                        visited[ny][nx][curNode.k - 1] = true;
                    }
                }
            }

        }

        System.out.println(dist == Integer.MAX_VALUE ? -1 : dist);
    }
}
