import java.io.*;
import java.util.*;

public class Main {
    static class Jewelry {
        int weight;
        int price;

        Jewelry(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewelry[] jewelries = new Jewelry[N];
        int[] bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            jewelries[i] = new Jewelry(w, p);
        }

        for (int i = 0; i < K; i++) {
            int c = Integer.parseInt(br.readLine());
            bags[i] = c;
        }

        Arrays.sort(jewelries, (o1, o2) -> o1.weight - o2.weight);
        Arrays.sort(bags);

        PriorityQueue<Jewelry> pq = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);

        long priceSum = 0;
        int j = 0;
        for (int i = 0; i < K; i++) {
            int bag = bags[i];
            while (j < N && jewelries[j].weight <= bag) {
                pq.offer(jewelries[j]);
                j++;
            }
            if (!pq.isEmpty()) {
                priceSum += pq.poll().price;
            }
        }

        System.out.println(priceSum);
    }
}
