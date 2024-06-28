import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[n + 1];
            boolean[] engage = new boolean[n + 1];
            boolean[] visited = new boolean[n + 1];
            boolean[] finished = new boolean[n + 1];

            for (int i = 1; i <= n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    dfs(arr, engage, visited, finished, i);
                }
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (!engage[i]) count++;
            }

            System.out.println(count);
        }
    }

    static void dfs(int[] arr, boolean[] engage, boolean[] visited, boolean[] finished, int start) {
        visited[start] = true;
        int next = arr[start];

        if (!visited[next]) {
            dfs(arr, engage, visited, finished, next);
        } else if (!finished[next]) {
            for (int i = next; i != start; i = arr[i]) {
                engage[i] = true;
            }
            engage[start] = true;
        }

        finished[start] = true;
    }
}
