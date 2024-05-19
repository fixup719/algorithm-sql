import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] W;
    static int[][][] dp;

    static int recur(int start, int prv, int selected) {

        if (selected == (1 << (N + 1)) - 2) {
            if (W[prv][start] == 0) return  1 << 30;
            return W[prv][start];
        }

        if (dp[start][prv][selected] != -1) return dp[start][prv][selected];

        int ret = 1 << 30;
        for (int i = 1; i <= N; i++) {
            if ((selected & (1 << i)) != 0 || W[prv][i] == 0) continue;

            ret = Math.min(ret, recur(start, i, selected | (1 << i)) + W[prv][i]);
        }

        dp[start][prv][selected] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        W = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N + 1][N + 1][1 << (N + 1)];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        int ans = 1 << 30;
        for (int i = 1; i <= N; i++) {
            ans = Math.min(ans, recur(i, i, 1 << i));
        }

        System.out.println(ans);

        bw.flush();
        bw.close();
        br.close();
    }
}