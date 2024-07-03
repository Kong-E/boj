import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String str = br.readLine();

            int count = 0;
            int sum = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == 'O') {
                    count++;
                    sum += count;
                } else {
                    count = 0;
                }
            }

            System.out.println(sum);
        }
    }
}