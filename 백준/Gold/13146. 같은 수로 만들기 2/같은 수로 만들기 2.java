import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int num = Integer.parseInt(br.readLine()), max = num, prv = num;
        long answer = 0;
        for (int i = 1; i < N; i++) {
            num = Integer.parseInt(br.readLine());
            if (prv < num) {
                if (max < num) answer += num - max;
            } else {
                answer += prv - num;
            }
            prv = num;
            max = Math.max(num, max);
        }

        System.out.println(answer);

    }
}