import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("1")) {
                stack.push(st.nextToken());
            } else if (command.equals("2")) {
                if (!stack.isEmpty()) {
                    append(stack.pop());
                } else {
                    append("-1");
                }
            } else if (command.equals("3")) {
                append(stack.size());
            } else if (command.equals("4")) {
                if (!stack.isEmpty()) {
                    append("0");
                } else {
                    append("1");
                }
            } else if (command.equals("5")) {
                if (!stack.isEmpty()) {
                    append(stack.peek());
                } else {
                    append("-1");
                }
            }
        }

        System.out.println(sb.toString());
    }

    static <T> void append(T str) {
        sb.append(str).append("\n");
    }
}