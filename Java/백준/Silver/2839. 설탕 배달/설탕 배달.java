import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.println(count(n));
    }

    static int count(int n) {
        for (int i = 0; i < 1700; i++) {
            for (int j = 0; j < 1001; j++) {
                int sum = 3*i + 5*j;
                if (sum == n) {
                    return i+j;
                }
            }
        }
        return -1;
    }
}
