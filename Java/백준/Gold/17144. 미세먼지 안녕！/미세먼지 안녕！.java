
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] board = new int[R][C];
        int[] cleaner = new int[2];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if (num == -1 && cleaner[0] == 0) {
                    cleaner[0] = i;
                    cleaner[1] = i + 1;
                }
            }
        }

        while (T-- > 0) {
            // 먼지 확산
            int[][] tempBoard = new int[R][C];
            tempBoard[cleaner[0]][0] = -1;
            tempBoard[cleaner[1]][0] = -1;

            for (int y = 0; y < R; y++) {
                for (int x = 0; x < C; x++) {
                    if (board[y][x] > 0) {
                        int amount = board[y][x] / 5;
                        int count = 0;
                        for (int i = 0; i < 4; i++) {
                            int nx = x + dx[i];
                            int ny = y + dy[i];

                            if (nx < 0 || nx >= C || ny < 0 || ny >= R) continue;
                            if (board[ny][nx] != -1) {
                                tempBoard[ny][nx] += amount;
                                count++;
                            }
                        }
                        tempBoard[y][x] += board[y][x] - (amount * count);
                    }
                }
            }

            for (int y = 0; y < R; y++) {
                for (int x = 0; x < C; x++) {
                    board[y][x] = tempBoard[y][x];
                }
            }

            // 공기 청정기 반시계방향
            for (int y = cleaner[0] - 1; y > 0; y--) board[y][0] = board[y - 1][0];
            for (int x = 0; x < C - 1; x++) board[0][x] = board[0][x + 1];
            for (int y = 0; y < cleaner[0]; y++) board[y][C - 1] = board[y + 1][C - 1];
            for (int x = C - 1; x > 1; x--) board[cleaner[0]][x] = board[cleaner[0]][x - 1];
            board[cleaner[0]][1] = 0;

            // 공기 청정기 시계방향
            for (int y = cleaner[1] + 1; y < R - 1; y++) board[y][0] = board[y + 1][0];
            for (int x = 0; x < C - 1; x++) board[R - 1][x] = board[R - 1][x + 1];
            for (int y = R - 1; y > cleaner[1]; y--) board[y][C - 1] = board[y - 1][C - 1];
            for (int x = C - 1; x > 1; x--) board[cleaner[1]][x] = board[cleaner[1]][x - 1];
            board[cleaner[1]][1] = 0;
        }

        int dust = 0;
        for (int i = 0; i < R; i++) {
            for (int j =0; j < C; j++) {
                if (board[i][j] > 0) dust += board[i][j];
            }
        }

        System.out.println(dust);
    }

    static void move() {

    }
}
