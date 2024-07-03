import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Map<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (!count.containsKey(current)) {
                count.put(current, i);
            }
        }

        for (int i = 0; i < 26; i++) {
            char current = (char) (i + 97);
            System.out.print(count.getOrDefault(current, -1) + " ");
        }
    }
}