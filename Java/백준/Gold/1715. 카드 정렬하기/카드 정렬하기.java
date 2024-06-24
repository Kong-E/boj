import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            pq.offer(num);
        }

        int cost = 0;
        if (n > 1) {
            while (pq.size() > 1) {
                int a = pq.poll();
                int b = pq.poll();
                pq.offer(a + b);
                cost += a + b;
            }
        }

        System.out.print(cost);
    }
}
