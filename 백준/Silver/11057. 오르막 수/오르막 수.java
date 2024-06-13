import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] dp;
    static final int MOD = 10_007;

    static int recur(int cur, int prv){
        if (cur == N) return 1;

        if (dp[cur][prv] != -1) return dp[cur][prv];

        int ret = 0;
        for (int i = prv; i <= 9; i++) {
            ret = (ret + recur(cur + 1, i) % MOD) % MOD;
        }

        dp[cur][prv] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N][10];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(recur(0, 0));

        br.close();
    }
}