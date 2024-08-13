import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static List<List<Integer>> tree = new ArrayList<>();
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        int length = (1 << K) - 1;
        int[] inorder = new int[length];

        for (int i = 0; i < K; i++) {
            tree.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < length; i++) {
            if (st.hasMoreTokens()) {
                inorder[i] = Integer.parseInt(st.nextToken());
            }
        }

        recursion(inorder, 0, length, 0);

        for (int i = 0; i < K; i++) {
            for (int node : tree.get(i)) {
                sb.append(node).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void recursion(int[] inorder, int start, int end, int depth) {
        if (depth == K) {
            return;
        }

        int mid = (start + end) / 2;

        tree.get(depth).add(inorder[mid]);
        recursion(inorder, start, mid - 1, depth + 1);
        recursion(inorder, mid + 1, end, depth + 1);
    }
}
