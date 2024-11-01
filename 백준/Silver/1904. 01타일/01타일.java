import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] dp = new int[1_000_001];
    static final int MOD = 15_746;

    static int recur(int cur) {
        if (cur > N) return 0;
        if (cur == N) return 1;

        if (dp[cur] != -1) return dp[cur];

        int ret = 0;
        // 1선택
        ret = (ret + recur(cur + 1) % MOD) % MOD;
        // 00선택
        ret = (ret + recur(cur + 2) % MOD) % MOD;
        dp[cur] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        Arrays.fill(dp, -1);
        int ret = recur(0);
        System.out.println(ret);
    }
}