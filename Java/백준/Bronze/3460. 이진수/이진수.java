import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0 ; i < T; i++) {
            int num = Integer.parseInt(br.readLine());
            recursion(num);

            String binary = sb.toString();

            sb.delete(0, sb.length());

            for (int j = 0; j < binary.length(); j++) {
                if (binary.charAt(j) == '1') sb.append(j).append(" ");
            }

            System.out.println(sb.toString());
            sb.delete(0, sb.length());
        }
    }

    static void recursion(int num) {
        if (num == 0) {
            return;
        }
        sb.append(num % 2);
        recursion(num / 2);
    }
}
