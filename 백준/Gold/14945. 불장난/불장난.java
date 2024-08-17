import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final int MOD = 10_007;
    static int[][][] dp;

    static int recur(int r, int c1, int c2) {

        if (r != 1 && c1 == c2) return 0;

        if (r == N) return 1;

        if (dp[r][c1][c2] != -1) return dp[r][c1][c2];

        int ret = 0;

        ret = (ret + recur(r + 1, c1,  c2 + 1) % MOD) % MOD;
        ret = (ret + recur(r + 1, c1, c2) % MOD) % MOD;
        ret = (ret + recur(r + 1, c1 + 1, c2) % MOD) % MOD;
        ret = (ret + recur(r + 1, c1 + 1, c2 + 1) % MOD) % MOD;

        dp[r][c1][c2] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[110][110][110];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                 Arrays.fill(d2, -1);
            }
        }

        int ret = recur(1, 1, 1);
        System.out.println(ret);
    }
}