import java.io.*;
import java.util.*;

public class Main {
    static Set<Integer> sums = new HashSet<>();
    static int n;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            nums[i] = num;
        }

        Arrays.sort(nums);

        dfs(0, 0);

        int missing = 1;
        while (true) {
            if (!sums.contains(missing)) break;
            missing++;
        }

        System.out.println(missing);
    }

    static void dfs(int start, int sum) {
        if (sum > 0) sums.add(sum);

        for (int i = start; i < n; i++) {
            dfs(i + 1, sum + nums[i]);
        }
    }
}
