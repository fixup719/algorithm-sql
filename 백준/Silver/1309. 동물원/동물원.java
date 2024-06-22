import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static final int MOD = 9901;
    static int[][] dp = new int[100010][3];

    static int recur(int row, int col) {
        if (row == N) return 1;

        if (dp[row][col] != -1) return dp[row][col];

        int ret = 0;

        // 다음 칸에 사자 배치 (가로 세로 걉치면 X)
        if (col != 1) ret = (ret + recur(row + 1, 1) % MOD) % MOD;

        if (col != 2) ret = (ret + recur(row + 1, 2) % MOD) % MOD;

        // 다음 칸에 사자 배치 X
        ret = (ret + recur(row + 1, 0) % MOD) % MOD;

        dp[row][col] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(0, 0);

        System.out.println(ans);
    }
}