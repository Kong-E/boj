import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    for (int y = 0; y < n; y++) {
                        for (int x = 0; x < m; x++) {
                            if (board[y][x] == 0) {
                                board[y][x] = 1;
                                for (int b = 0; b < n; b++) {
                                    for (int a = 0; a < m; a++) {
                                        if (board[b][a] == 0) {
                                            board[b][a] = 1;
                                            int count = 0;
                                            boolean[][] visited = new boolean[n][m];
                                            for (int k = 0; k < n; k++) {
                                                for (int z = 0; z < m; z++) {
                                                    if (board[k][z] == 2 && !visited[k][z]) {
                                                        bfs(z, k, visited);
                                                    }
                                                }
                                            }
                                            for (int k = 0; k < n; k++) {
                                                for (int z = 0; z < m; z++) {
                                                    if (board[k][z] == 0) {
                                                        count++;
                                                    } else if (board[k][z] == -2) {
                                                        board[k][z] = 0;
                                                    }
                                                }
                                            }
                                            answer = Math.max(answer, count);

                                            board[b][a] = 0;
                                        }
                                    }
                                }
                                board[y][x] = 0;
                            }
                        }
                    }
                    board[i][j] = 0;
                }
            }
        }

        System.out.println(answer);
    }

    static void bfs(int startX, int startY, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {startX, startY});
        visited[startY][startX] = true;

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0];
            int y = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n) continue;

                if (board[ny][nx] == 0 && !visited[ny][nx]) {
                    q.offer(new int[] {nx, ny});
                    board[ny][nx] = -2;
                    visited[ny][nx] = true;
                }
            }
        }
    }
}
