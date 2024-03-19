import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N + 100];

        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < N + 1; i++) {
            dp[i] = dp[i - 1] % 10007 + dp[i - 2] % 10007;
        }

        bw.write(String.valueOf(dp[N] % 10007));
        bw.flush();
        bw.close();
        br.close();
    }
}