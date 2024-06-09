import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] dp;

    static int recur(int remain, int turn) {
        // 돌 1개 또는 3개

        if (remain <= 0) return turn;

        if (dp[remain][turn] != -1) return dp[remain][turn];

        int ret;
        if (turn == 0) {
            ret = 2;
            ret = Math.min(ret, recur(remain - 1, 1));
            ret = Math.min(ret, recur(remain - 3, 1));
        } else {
            ret = -1;
            ret = Math.max(ret, recur(remain - 1, 0));
            ret = Math.max(ret, recur(remain - 3, 0));
        }

        dp[remain][turn] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 10][2];
        for (int[] d1 : dp) {
            Arrays.fill(d1, -1);
        }

        int ans = recur(N, 0);
        if (ans == 1) System.out.println("SK");
        else System.out.println("CY");

        br.close();
    }
}