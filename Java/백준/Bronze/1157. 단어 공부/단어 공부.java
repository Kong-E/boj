import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        Map<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char curChar = str.charAt(i);
            char upperCurChar = Character.toUpperCase(curChar);
            count.put(upperCurChar, count.getOrDefault(upperCurChar, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> list = new ArrayList<>(count.entrySet());
        Collections.sort(list, Map.Entry.comparingByValue(Collections.reverseOrder()));

        if (list.size() > 1 && list.get(0).getValue().equals(list.get(1).getValue())) System.out.println("?");
        else System.out.println(list.get(0).getKey());
    }
}