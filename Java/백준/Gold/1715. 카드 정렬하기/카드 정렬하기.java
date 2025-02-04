import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int sum = 0;
        while (!pq.isEmpty() && n != 1) {
            int mix = pq.poll();
            if (!pq.isEmpty()) {
                int poll = pq.poll();
                mix += poll;
                if (!pq.isEmpty()) {
                    pq.offer(mix);
                }
            }
            sum += mix;
        }

        System.out.println(sum);
    }
}