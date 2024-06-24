import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            int[] nums = new int[n];

            int idx = 0;
            for (int i = 0; i < n / 10 + 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    nums[idx++] = Integer.parseInt(st.nextToken());
                }
            }

            PriorityQueue<Integer> minPq = new PriorityQueue<>();
            PriorityQueue<Integer> maxPq = new PriorityQueue<>(Comparator.reverseOrder());
            List<Integer> mids = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int num = nums[i];

                if (maxPq.isEmpty() || maxPq.peek() > num) {
                    maxPq.offer(num);
                } else {
                    minPq.offer(num);
                }

                if (maxPq.size() > minPq.size() + 1) {
                    minPq.offer(maxPq.poll());
                } else if (minPq.size() > maxPq.size()) {
                    maxPq.offer(minPq.poll());
                }

                if (i % 2 == 0) {
                    mids.add(maxPq.peek());
                }
            }

            System.out.println(mids.size());
            for (int i = 0; i < mids.size(); i++) {
                if (i != 0 && i % 10 == 0) {
                    System.out.println();
                }
                int mid = mids.get(i);
                System.out.print(mid + " ");
            }
            System.out.println();
        }
    }
}
