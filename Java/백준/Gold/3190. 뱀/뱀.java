import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            board[r][c] = 1; // 사과
        }

        int L = Integer.parseInt(br.readLine());
        Map<Integer, String> directions = new HashMap<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            directions.put(x, c);
        }

        ArrayDeque<int[]> deque = new ArrayDeque<>();
        board[0][0] = 2;
        int direction = 0;
        deque.offer(new int[] {0, 0});
        int x = 0; int y = 0;
        int time = 0;

        while (true) {
            time++;
            x += dx[direction];
            y += dy[direction];

            if (x < 0 || x >= N || y < 0 || y >= N || board[y][x] == 2) break;

            if (board[y][x] == 1) {
                board[y][x] = 2;
                deque.offer(new int[] {x, y});
            } else {
                board[y][x] = 2;
                deque.offer(new int[] {x, y});
                int[] pop = deque.pollFirst();
                board[pop[1]][pop[0]] = 0;
            }

            if (directions.containsKey(time)) {
                String d = directions.get(time);

                if (d.equals("L")) direction = (direction + 3) % 4;
                else direction = (direction + 1) % 4;
            }
        }

        System.out.println(time);
    }
}
