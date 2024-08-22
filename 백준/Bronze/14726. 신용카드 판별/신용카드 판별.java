import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String num; int tmp; long sum;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            num = br.readLine();
            sum = 0;
            for (int j = num.length() - 1; j >= 0; j--) {
                if (j % 2 == 0) {
                    tmp = 2 * (num.charAt(j) - '0');
                    if (tmp >= 10) tmp = (tmp / 10) + (tmp % 10);
                    sum += tmp;
                } else {
                    sum += num.charAt(j) - '0';
                }
            }
            if (sum % 10 == 0) sb.append("T\n");
            else sb.append("F\n");
        }
        System.out.println(sb);
    }
}