import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] coins = new int[30];
    static int[][] dp = new int[30][10010];

    static int recur(int cur, int remain) {
        if (remain < 0) return 0;

        if (cur == N) {
            if (remain == 0) return 1;
            else return 0;
        }

        if (dp[cur][remain] != -1) return dp[cur][remain];

        int ret = 0;
        ret += recur(cur, remain - coins[cur]) + recur(cur + 1, remain);

        dp[cur][remain] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());

            for (int[] ints : dp) {
                Arrays.fill(ints, -1);
            }

            sb.append(recur(0, M)).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}