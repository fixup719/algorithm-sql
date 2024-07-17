import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] dp;

    static int recur(int cur) {
        if (cur >= N) return 1;

        if (dp[cur] != -1) return dp[cur];

        int ret = 0;

        // 내 오른쪽과 악수
        if (cur + 1 < N) ret = (ret + recur(cur + 2) % 10) % 10;

        // 악수 X
        ret = (ret + recur(cur + 1) % 10) % 10;

        dp[cur] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[10000010];
        Arrays.fill(dp, -1);

        int ans = recur(0);

        System.out.println(ans % 10);
    }
}