import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] cost;
    static int[][][] dp;

    static int backtracking(int cur, int first, int prv) {
        if (cur == N) return 0;

        if (dp[cur][first][prv] != -1) return dp[cur][first][prv];

        int ret = 1 << 30;
        for (int i = 1; i <= 3; i++) {
            if ((cur == N - 1 && first == i) || prv == i) continue;

            if (cur == 0) {
                ret = Math.min(ret, backtracking(cur + 1, i, i) + cost[cur][i]);
            } else {
                ret = Math.min(ret, backtracking(cur + 1, first, i) + cost[cur][i]);
            }

        }

        dp[cur][first][prv] = ret;
        return dp[cur][first][prv];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        cost = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
            cost[i][3] = Integer.parseInt(st.nextToken());
        }

        dp = new int[1010][4][4];
        for (int[][] ints : dp) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }

        bw.write(String.valueOf(backtracking(0, 0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }
}