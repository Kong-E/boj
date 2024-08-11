import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) {
            strs[i] = br.readLine();
        }

        Arrays.sort(strs);

        int count = 0;
        for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(strs[i - 1])) {
                count++;
            }
        }

        System.out.println(count + 1);
    }
}