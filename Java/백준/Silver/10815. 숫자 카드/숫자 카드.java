
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] ns = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            ns[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(ns);

        int m = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int target;
        for (int i = 0; i < m; i++) {
            target = Integer.parseInt(st.nextToken());
            if (Arrays.binarySearch(ns, target) >= 0) {
                sb.append(1 + " ");
            } else {
                sb.append(0 + " ");
            }
        }

        System.out.println(sb.toString());
    }
}