import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, 1, 1, 0};
    static int[] dy = {0, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] board = new int[19][19];

        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean found = find(board);

        if (!found) System.out.println(0);
    }

    static boolean find(int[][] board) {
        for (int y = 0; y < 19; y++) {
            for (int x = 0; x < 19; x++) {
                int current = board[y][x];
                if (current != 0) {
                    for (int i = 0; i < 4; i++) { // 4가지 방향
                        int nx = x;
                        int ny = y;
                        int count = 1;
                        
                        while (true) {
                            nx += dx[i];
                            ny += dy[i];

                            if (nx < 0 || nx >= 19 || ny < 0 || ny >= 19 || board[ny][nx] != current) {
                                break;
                            }

                            count++;

                            if (count == 5) {
                                int isBackSixX = nx + dx[i];
                                int isBackSixY = ny + dy[i];
                                if (isBackSixY >= 0 && isBackSixY < 19 && isBackSixX >= 0 && isBackSixX < 19 && board[isBackSixY][isBackSixX] == current) {
                                    break;
                                }
                                int isFrontSixX = x - dx[i];
                                int isFrontSixY = y - dy[i];
                                if (isFrontSixX >= 0 && isFrontSixX < 19 && isFrontSixY >= 0 && isFrontSixY < 19 && board[isFrontSixY][isFrontSixX] == current) {
                                    break;
                                }
                                System.out.println(current);
                                System.out.print((y + 1) + " ");
                                System.out.print((x + 1));
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
