import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long S = Long.parseLong(br.readLine());
        int count = 0;
        long sum = 0;

        for (int i = 1; ; i++) {
            if (sum + i > S) break;
            sum += i;
            count++;
        }

        System.out.println(count);
    }
}
