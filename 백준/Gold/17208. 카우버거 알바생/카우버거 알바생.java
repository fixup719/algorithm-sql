import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] order;
    static int[][][] dp = new int[110][310][310];

    // 메모이제이션 안 할 경우 시간복잡도 => 2 ^ (100*300*300)
    static int dfs(int cur, int burger, int potato) {
        if (burger < 0 || potato < 0) return Integer.MIN_VALUE;
        if (cur == N) return 0;
        if (dp[cur][burger][potato] != -1) return dp[cur][burger][potato];

        int ret = 0;
        // 현재 주문 처리 O
        ret = Math.max(ret, dfs(cur + 1
                , burger - order[cur][0]
                , potato - order[cur][1]) + 1);
        // 현재 주문 처리 X
        ret = Math.max(ret, dfs(cur + 1, burger, potato));
        dp[cur][burger][potato] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        order = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            order[i][0] = Integer.parseInt(st.nextToken());
            order[i][1] = Integer.parseInt(st.nextToken());
        }
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        int ret = dfs(0, M, K);
        System.out.println(ret);
    }
}

