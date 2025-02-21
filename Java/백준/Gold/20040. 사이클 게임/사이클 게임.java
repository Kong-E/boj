import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parents = new int[n];

        Arrays.fill(parents, -1);

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (find(a) == find(b)) {
                sb.append(i + 1);
                break;
            } else {
                union(a, b);
            }
        }

        System.out.println(sb.length() > 0 ? sb.toString() : 0);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        parents[parentB] = parentA;
    }

    static int find(int num) {
        if (parents[num] == num) return num;

        return parents[num] = find(parents[num]);
    }
}