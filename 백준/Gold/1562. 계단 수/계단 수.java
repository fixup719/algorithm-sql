import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] selected = new int[110];
    static int[][][] dp = new int[110][10][1 << 10];
    static final int MOD = 1_000_000_000;


    static int recur(int cur, int prv, int numCnt) {
        if (cur == N) {
            if (numCnt == 1023) {
                return 1;
            } else {
                return 0;
            }
        }

        if (dp[cur][prv][numCnt] != -1) return dp[cur][prv][numCnt];

        int ret = 0;
        for (int i = 0; i < 10; i++) {

            // 이전 숫자와의 차이가 1이 아니라면 pass
            if (Math.abs(prv - i) != 1) continue;

            selected[cur] = i;

            ret = (ret + recur(cur + 1, i, numCnt | (1 << i)) % MOD) % MOD;

        }

        dp[cur][prv][numCnt] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        int ans = 0;
        for (int i = 1; i <= 9; i++) {
            ans = (ans + recur(1, i, 1 << i) % MOD) % MOD;
        }

        System.out.println(ans);
        br.close();
    }
}