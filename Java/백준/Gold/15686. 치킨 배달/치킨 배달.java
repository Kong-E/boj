import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static List<int[]> chickens = new ArrayList<>();
    static List<int[]> houses = new ArrayList<>();
    static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
                if (num == 2) {
                    chickens.add(new int[] {j, i});
                }
                if (num == 1) {
                    houses.add(new int[] {j, i});
                }
            }
        }
        int[][] chosen = new int[M][2];
        dfs(chosen,0, 0);

        System.out.println(minDist);
    }

    static void dfs(int[][] chosen, int startIdx, int depth) {
        if (depth == M) {
            minDist = Math.min(minDist, calculateMinDist(chosen));
            return;
        }

        for (int i = startIdx; i < chickens.size(); i++) {
            int[] cur = chickens.get(i);
            chosen[depth] = cur;
            dfs(chosen, i + 1, depth + 1);
        }
    }

    static int calculateMinDist(int[][] chosen) {
        int dist = 0;
        for (int[] curHouse : houses) {
            int curHouseX = curHouse[0];
            int curHouseY = curHouse[1];
            int curHouseMinDist = Integer.MAX_VALUE;
            for (int[] curChicken : chosen) {
                int curChickenX = curChicken[0];
                int curChickenY = curChicken[1];
                int curDist = Math.abs(curChickenX - curHouseX) + Math.abs(curChickenY - curHouseY);
                curHouseMinDist = Math.min(curDist, curHouseMinDist);
            }
            dist += curHouseMinDist;
        }
        return dist;
    }
}
