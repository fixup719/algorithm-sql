import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final int MOD = 1_000_000;
    static int[][][][] dp = new int[1010][1010][3][4];

    // 개근상 받을 수 없는 경우 -> 지각 두 번 이상 or 결석 세번 연속
    // 개근상 받을 수 있는 사람 -> 지각 한 번 && 결석 세번 연속 X

    static int recur(int cur, int o, int l, int a) {
        if (l >= 2 ||  a >= 3) return 0;

        if (cur == N) return 1;

        if (dp[cur][o][l][a] != -1) return dp[cur][o][l][a];

        int ret = 0;
        // 출석
        ret = (ret + recur(cur + 1, o + 1, l, 0) % MOD) % MOD;
        // 지각
        ret = (ret + recur(cur + 1, o, l + 1, 0) % MOD) % MOD;
        // 결석
        ret = (ret + recur(cur + 1, o, l, a + 1) % MOD) % MOD;

        dp[cur][o][l][a] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int[][][] d1 : dp) {
            for (int[][] d2 : d1) {
                for (int[] d3 : d2) {
                    Arrays.fill(d3, -1);
                }
            }
        }

        int ans = recur(0, 0, 0, 0);

        System.out.println(ans);
    }
}