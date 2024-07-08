import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int[] seq = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());

        boolean[][] dp = new boolean[N + 1][N + 1];

        // i번째 수부터 i번째 수일 경우
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
        }
        // i번째 수부터 i + 1번째 수일 경우
        for (int i = 1; i <= N - 1; i++) {
            if (seq[i] == seq[i + 1]) {
                dp[i][i + 1] = true;
            } else {
                dp[i][i + 1] = false;
            }
        }
        // i번째 수부터 j(j >= i + 2)번째 수일 경우
        for (int len = 2; len <= N - 1; len++) {
            for (int i = 1; i <= N - len; i++) {
                if (seq[i] == seq[i + len]) {
                    dp[i][i + len] = dp[i + 1][i + len - 1];
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            boolean answer = dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
            if (answer) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }

        System.out.println(sb);
    }
}
