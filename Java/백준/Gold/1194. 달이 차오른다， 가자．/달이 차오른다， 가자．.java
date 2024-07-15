import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        int x, y, keys, steps;

        Point(int x, int y, int keys, int steps) {
            this.x = x;
            this.y = y;
            this.keys = keys;
            this.steps = steps;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 세로
        int m = Integer.parseInt(st.nextToken()); // 가로
        char[][] board = new char[n][m];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Point start = null;

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                char num = row.charAt(j);
                board[i][j] = num;
                if (num == '0') {
                    start = new Point(j, i, 0, 0);
                }
            }
        }

        boolean[][][] visited = new boolean[n][m][64];
        visited[start.y][start.x][start.keys] = true;
        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        int ans = -1;
        while (!q.isEmpty()) {
            Point current = q.poll();
            int x = current.x;
            int y = current.y;
            int keys = current.keys;
            int steps = current.steps;

            if (board[y][x] == '1') {
                ans = steps;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int newKeys = keys;
                int newSteps = steps + 1;

                if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                char next = board[ny][nx];

                if (next == '#') continue;

                if ('a' <= next && next <= 'f') {
                    newKeys |= (1 << next - 'a');
                }

                if ('A' <= next && next <= 'F') {
                    if ((newKeys & (1 << next - 'A')) == 0) continue;
                }

                if (!visited[ny][nx][newKeys]) {
                    visited[ny][nx][newKeys] = true;
                    q.offer(new Point(nx, ny, newKeys, newSteps));
                }
            }
        }

        System.out.println(ans);
    }
}
