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
        while (pq.size() > 1) {
            int poll1 = pq.poll();
            int poll2 = pq.poll();
            int mix = poll1 + poll2;
            pq.offer(mix);
            sum += mix;
        }

        System.out.println(sum);
    }
}