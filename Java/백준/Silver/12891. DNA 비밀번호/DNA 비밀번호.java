import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        String DNA = br.readLine();
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int count = 0;
        int[] acgt = new int[4];

        for (int i = 0; i < P; i++) {
            char curChar = DNA.charAt(i);
            plus(acgt, curChar);
        }
        if (acgt[0] >= A && acgt[1] >= C && acgt[2] >= G && acgt[3] >= T) count++;

        int l = 0;
        int r = P;
        while (r < S) {
            char curLeftChar = DNA.charAt(l);
            minus(acgt, curLeftChar);
            char curRightChar = DNA.charAt(r);
            plus(acgt, curRightChar);
            if (acgt[0] >= A && acgt[1] >= C && acgt[2] >= G && acgt[3] >= T) count++;

            l++;
            r++;
        }

        System.out.println(count);
    }

    static void minus(int[] acgt, char curChar) {
        if (curChar == 'A') acgt[0]--;
        if (curChar == 'C') acgt[1]--;
        if (curChar == 'G') acgt[2]--;
        if (curChar == 'T') acgt[3]--;
    }
    static void plus(int[] acgt, char curChar) {
        if (curChar == 'A') acgt[0]++;
        if (curChar == 'C') acgt[1]++;
        if (curChar == 'G') acgt[2]++;
        if (curChar == 'T') acgt[3]++;
    }
}
