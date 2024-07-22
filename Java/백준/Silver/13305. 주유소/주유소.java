import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[] distance = new long[N - 1];
        long[] cities = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            distance[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cities[i] = Integer.parseInt(st.nextToken());
        }

        long minCost = cities[0];
        long cost = 0;

        for (int i = 0; i < N - 1; i++) {
            if (cities[i] < minCost) {
                minCost = cities[i];
            }
            cost += minCost * distance[i];
        }

        System.out.println(cost);
    }
}
