import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int quarter = 0, dime = 0, nickel = 0, penny = 0;

            quarter += n / 25;
            n -= (n / 25) * 25;

            dime += n / 10;
            n -= (n / 10) * 10;

            nickel += n / 5;
            n -= (n / 5) * 5;

            penny += n;

            System.out.println(quarter + " " + dime + " " + nickel + " " + penny);
        }
    }
}