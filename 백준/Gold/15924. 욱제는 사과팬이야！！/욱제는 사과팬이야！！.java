import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] dp;
    static final int MOD = 1000000009;

    static int recur(int row, int col) {
        if (N <= row || M <= col) return 0;

        if (row == N - 1 && col == M - 1) return 1;

        if (dp[row][col] != -1) return dp[row][col];

        int ret = 0;

        if (map[row][col] == 'E') {
            ret = (ret + recur(row, col + 1) % MOD) % MOD;
        } else if (map[row][col] == 'S') {
            ret = (ret + recur(row + 1, col) % MOD) % MOD;
        } else if (map[row][col] == 'B') {
            ret = (ret + recur(row, col + 1) % MOD) % MOD;
            ret = (ret + recur(row + 1, col) % MOD) % MOD;
        }

        dp[row][col] = ret % MOD;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        dp = new int[N + 10][M + 10];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans = (ans + recur(i, j) % MOD) % MOD;
            }
        }

        System.out.println(ans % MOD);
    }
}