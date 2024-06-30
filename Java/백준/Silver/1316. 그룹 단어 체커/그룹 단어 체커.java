import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 0; i < n; i++) {
            List<Character> list = new ArrayList<>();
            String word = br.readLine();
            int len = word.length();

            for (int j = 0; j < len; j++) {
                if (!list.contains(word.charAt(j))) {
                    list.add(word.charAt(j));
                } else {
                    if (j - 1 >= 0 && word.charAt(j) != word.charAt(j-1)) {
                        break;
                    }
                }
                if (j == len - 1) count++;
            }
        }

        System.out.println(count);

    }
}
