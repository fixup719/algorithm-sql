import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] dp;

    static int recur(int cur, int prv) {

        if (cur == N + 1) return 1;

        if (dp[cur][prv] != -1) return dp[cur][prv];

        int ret = 0;
        for (int i = prv; i <= M; i++) {
            ret += recur(cur + 1, i + 1);
        }

        dp[cur][prv] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            dp = new int[N + 10][M + 10];
            for (int[] d1 : dp) {
                Arrays.fill(d1, -1);
            }

            sb.append(recur(1, 1)).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}