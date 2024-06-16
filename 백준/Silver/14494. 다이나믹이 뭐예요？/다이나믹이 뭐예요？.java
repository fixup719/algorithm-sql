import java.io.*;
import java.util.*;

/*
    (1,1) -> (N,M)까지 도착하는 경우읫
        우 하 우하 방향으로만 이동
        N과 M은 1000까지 가능
 */

public class Main {
    static int N, M;
    static int[][] dp;
    static int[] delR = {0, 1, 1};
    static int[] delC = {1, 0, 1};
    static final int MOD = 1_000_000_007;

    static int recur(int row, int col) {

        if (row > N || col > M) return 0;

        if (row == N && col == M) return 1;

        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        int ret = 0;
        for (int dir = 0; dir < 3; dir++) {
            ret = (ret + recur(row + delR[dir], col + delC[dir]) % MOD) % MOD;
        }

        dp[row][col] = ret;
        return ret;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 10][M + 10];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(recur(1, 1));

        br.close();
    }
}