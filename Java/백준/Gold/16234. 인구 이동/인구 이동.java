import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int N;
    static int L;
    static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int totalCount = 0;
        while (true) {
            boolean[][] visited = new boolean[N][N];
            boolean isMoved = false;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (!visited[y][x]) {
                        int unionSize = bfs(board, x, y, visited);

                        if (unionSize > 1) {
                            isMoved = true;
                        }
                    }
                }
            }

            if (!isMoved) break;
            totalCount++;
        }

        System.out.println(totalCount);
    }

    static int bfs(int[][] board, int x, int y, boolean[][] visited) {
        List<int[]> union = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = true;
        q.offer(new int[] {x, y});
        union.add(new int[] {x, y});
        int totalPopulation = board[y][x];

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curX = cur[0];
            int curY = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = curX + dx[i];
                int ny = curY + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (visited[ny][nx]) continue;
                int diff = Math.abs(board[curY][curX] - board[ny][nx]);
                if (diff >= L && diff <= R) {
                    visited[ny][nx] = true;
                    q.offer(new int[] {nx, ny});
                    union.add(new int[] {nx, ny});
                    totalPopulation += board[ny][nx];
                }
            }
        }

        int newPopulation = totalPopulation / union.size();

        for (int[] cur : union) {
            int curX = cur[0];
            int curY = cur[1];

            board[curY][curX] = newPopulation;
        }

        return union.size();
    }
}
