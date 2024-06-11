import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] dp;
    static final int MOD = 1_000_000_000;

    // 계단수 : 인접한 모든 자리의 차이가 1

    static int recur(int cur, int prv) {
        if (cur == N) return 1;

        if (dp[cur][prv] != -1) return dp[cur][prv];

        int ret = 0;
        for (int i = 0; i < 10; i++) {
            if (cur == 0 && i == 0) continue;
            if (cur != 0 && Math.abs(prv - i) != 1) continue;

            ret = (ret + recur(cur + 1, i) % MOD) % MOD;
        }

        dp[cur][prv] = ret;

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 10][10];
        for (int[] d1 : dp) {
            Arrays.fill(d1, -1);
        }

        System.out.println(recur(0, 0));

        br.close();
    }
}