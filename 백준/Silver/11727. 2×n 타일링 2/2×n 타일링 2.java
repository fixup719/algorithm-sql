import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static final int MOD = 10_007;
    static int[] dp = new int[1001];

    static int recur(int cur) {
        if (cur > N) return 0;
        if (cur == N) return 1;

        if (dp[cur] != -1) return dp[cur];

        int ret = 0;
        // 1*2타일링으로 채우기
        ret = (ret + recur(cur + 2) % MOD) % MOD;
        // 2*1타일링으로 채우기
        ret = (ret + recur(cur + 1) % MOD) % MOD;
        // 2*2타일링으로 채우기
        ret = (ret + recur(cur + 2) % MOD) % MOD;

        dp[cur] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        Arrays.fill(dp, -1);
        System.out.println(recur(0));
    }
}

