import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final int MOD = 100_999;
    static int[][] dp = new int[2020][2020];

    static int recur(int cur, int sum) {
        if (sum > N) return 0;

        if (cur == N + 1) {
            if (sum == N) return 1;
            return 0;
        }

        if (dp[cur][sum] != -1) return dp[cur][sum];

        int ret = 0;

        ret = (ret + recur(cur + 1, sum + cur) % MOD) % MOD;
        ret = (ret + recur(cur + 1, sum) % MOD) % MOD;

        dp[cur][sum] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }

            int ans = recur(1, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}