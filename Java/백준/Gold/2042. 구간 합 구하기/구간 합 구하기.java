
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 수의 개수
        int m = Integer.parseInt(st.nextToken()); // 수의 변경이 일어나는 횟수
        int k = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수

        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        long[] tree = new long[4 * n];

        init(tree, arr, 0, n - 1, 1);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a.equals("1")) {
                long diff = c - arr[b - 1];
                arr[b - 1] = c;
                replace(tree,0, n - 1,b - 1, diff, 1);
            }
            if (a.equals("2")) {
                sb.append(sum(tree, 0, n - 1, b - 1, (int) c - 1, 1)).append("\n");
            }
        }

        System.out.println(sb);
    }

    static long init(long[] tree, long[] arr, int start, int end, int node) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end) / 2;
        return tree[node] = init(tree, arr, start, mid, node * 2)
                + init(tree, arr, mid + 1, end, node * 2 + 1);
    }

    // left와 right이 target 구간
    static long sum(long[] tree, int start, int end, int left, int right, int node) {
        if (left > end || start > right) return 0;
        if (start >= left && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(tree, start, mid, left, right, 2 * node)
                + sum(tree, mid + 1, end, left, right, 2 * node + 1);
     }

     static void replace(long[] tree, int start, int end, int index, long diff, int node) {
        if (index >= start && index <= end) {
            tree[node] += diff;
            if (start == end) return;
            int mid = (start + end) / 2;
            replace(tree, start, mid, index, diff, 2 * node);
            replace(tree, mid + 1, end, index, diff, 2 * node + 1);
        }
     }
}
