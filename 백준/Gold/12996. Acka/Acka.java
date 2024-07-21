import java.io.*;
import java.util.*;

public class Main {
    static int S, D, K, H;
    static int[][] sing = {
            // 한명씩 부르기
            {1, 0, 0}, {0, 1, 0}, {0, 0, 1},
            // 두명씩 부르기
            {1, 1, 0}, {0, 1, 1}, {1, 0, 1},
            // 세명씩 부르기
            {1, 1, 1}
    };
    static int[][][][] dp;
    static final int MOD = 1_000_000_007;

    static int recur(int cur, int rd, int rk, int rh) {
        if (rd < 0 || rk < 0 || rh < 0) return 0;

        if (cur == S) {
            if (rd > 0 || rk > 0 || rh > 0) return 0;
            return 1;
        }

        if (dp[cur][rd][rk][rh] != -1) return dp[cur][rd][rk][rh];

        int ret = 0;
        for (int i = 0, len = sing.length; i < len; i++) {
            ret = (ret + recur(cur + 1, rd - sing[i][0], rk - sing[i][1], rh - sing[i][2]) % MOD) % MOD;
        }

        dp[cur][rd][rk][rh] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        dp = new int[S + 10][D + 10][K + 10][H + 10];
        for (int[][][] d1 : dp) {
            for (int[][] d2 : d1) {
                for (int[] d3 : d2) {
                    Arrays.fill(d3, -1);
                }
            }
        }

        int ans = recur(0, D, K, H);
        System.out.println(ans);
    }
}