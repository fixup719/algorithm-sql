import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] foods;
    static int[][] dp = new int[100010][11];

    static int recur(int cur, int prv) {
        if (cur == N) return 0;

        if (dp[cur][prv] != -1) return dp[cur][prv];

        int ret = 0, taste;
        for (int i = 1; i <= M; i++) {
            if (prv == i) taste = foods[i][cur] / 2;
            else taste = foods[i][cur];

            ret = Math.max(ret, recur(cur + 1, i) + taste);
        }

        dp[cur][prv] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        foods = new int[M + 1][N];

        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                foods[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(0, 0);

        System.out.println(ans);
    }
}