import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] dp;

    static int dfs(int cur, int pre) {
        if (cur == N) return 0;

        if (dp[cur][pre] != -1) return dp[cur][pre];

        int ret = 1 << 30;
        for (int i = 1; i <= M; i++) {
            if (pre == i) continue;
            ret = Math.min(ret, dfs(cur+1, i) + arr[cur][i]);
        }

        dp[cur][pre] = ret;
        return ret;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N][M + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = dfs(0, 0);
        System.out.println(ans);
    }
}