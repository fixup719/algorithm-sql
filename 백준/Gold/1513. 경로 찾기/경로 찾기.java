import java.io.*;
import java.util.*;


public class Main {
    static int N, M, C;
    static int[][] map;
    static int[] delR = {1, 0};
    static int[] delC = {0, 1};
    static int[][][][] dp;
    static final int MOD = 1_000_007;

    static int recur(int row, int col, int vCnt, int prv) {

        if (vCnt < 0) return 0;

        if (row == N - 1 && col == M - 1) {
            if (vCnt == 0) return 1;
            else return 0;
        }

        if (dp[row][col][vCnt][prv] != -1) return dp[row][col][vCnt][prv];

        int mr, mc, ret = 0;
        for (int dir = 0; dir < 2; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (N <= mr || M <= mc) continue;

            if (map[mr][mc] > 0) {
                // 오락실일 경우
                if (prv < map[mr][mc]) ret = (ret + recur(mr, mc, (vCnt - 1) % MOD, map[mr][mc]) % MOD) % MOD;
            } else {
                ret = (ret + recur(mr, mc, vCnt, prv) % MOD) % MOD;
            }
        }

        dp[row][col][vCnt][prv] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        int r, c;
        for (int i = 1; i <= C; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = i;
        }

        dp = new int[N + 10][M + 10][C + 10][C + 10];
        for (int[][][] d1 : dp) {
            for (int[][] d2 : d1) {
                for (int[] d3 : d2) {
                    Arrays.fill(d3, -1);
                }
            }
        }

        int ans = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= C; i++) {
            if (map[0][0] > 0) ans = recur(0, 0, i - 1, map[0][0]);
            else ans = recur(0, 0, i, 0);

            sb.append(ans).append(" ");
        }

        System.out.println(sb);

        br.close();
    }
}