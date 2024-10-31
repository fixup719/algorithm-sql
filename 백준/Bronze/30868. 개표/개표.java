import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n, a, b;
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());
            a = n / 5;
            b = n % 5;

            for (int i = 0; i < a; i++) {
                sb.append("++++ ");
            }

            for (int i = 0; i < b; i++) {
                sb.append("|");
            }

            sb.append("\n");
        }

        System.out.println(sb);
    }
}