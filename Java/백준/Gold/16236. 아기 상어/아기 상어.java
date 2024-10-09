import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][n];
        int status; // 공간의 상태
        int[] start = new int[3]; // x, y, dist

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                status = Integer.parseInt(st.nextToken());
                board[i][j] = status;

                if (status == 9) {
                    start = new int[] {j, i, 0};
                    board[i][j] = 0;  // 상어의 시작점은 빈 칸으로 설정
                }
            }
        }

        System.out.println(bfs(board, n, start));
    }

    static int bfs(int[][] board, int n, int[] start) {
        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};

        int distance = 0; // 총 이동 거리
        int size = 2;     // 처음 상어 크기
        int ate = 0;      // 먹은 물고기 수

        while (true) {
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
                if (o1[2] == o2[2]) {  // 거리가 같으면
                    if (o1[1] == o2[1]) {  // y좌표가 같으면
                        return o1[0] - o2[0];  // x좌표 비교 (왼쪽 우선)
                    }
                    return o1[1] - o2[1];  // y좌표 비교 (위쪽 우선)
                }
                return o1[2] - o2[2];  // 거리 비교
            });
            boolean[][] visited = new boolean[n][n];
            pq.offer(start);
            visited[start[1]][start[0]] = true;

            boolean found = false;
            int[] target = null;

            while (!pq.isEmpty()) {
                int[] current = pq.poll();
                int x = current[0];
                int y = current[1];
                int dist = current[2];

                // 먹을 수 있는 물고기 찾았을 때
                if (board[y][x] > 0 && board[y][x] < size) {
                    target = new int[] {x, y, dist};
                    found = true;
                    break;
                }

                // 상하좌우 이동
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[ny][nx] && board[ny][nx] <= size) {
                        pq.offer(new int[] {nx, ny, dist + 1});
                        visited[ny][nx] = true;
                    }
                }
            }

            // 더 이상 먹을 물고기가 없을 때
            if (!found) {
                break;
            }

            // 먹은 물고기로 이동 및 거리 증가
            start = new int[] {target[0], target[1], 0};
            distance += target[2];
            board[start[1]][start[0]] = 0;  // 물고기 먹은 자리는 빈 칸으로

            // 먹은 물고기 수가 상어 크기와 같으면 상어 크기 증가
            if (++ate == size) {
                size++;
                ate = 0;
            }
        }

        return distance;
    }
}
