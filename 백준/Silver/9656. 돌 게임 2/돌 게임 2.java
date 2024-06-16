import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] getStones = {1, 3};
    static int[][] dp;

    static int recur(int remain, int turn) {
        if (remain == 0) return turn;

        if (dp[remain][turn] != -1) return dp[remain][turn];

        int ret;
        if (turn == 0) {
            ret = 1;
            for (int i = 0; i < 2; i++) {
                if (remain - getStones[i] < 0) continue;
                ret = Math.min(ret, recur(remain - getStones[i], 1));
            }
        } else {
            ret = 0;
            for (int i = 0; i < 2; i++) {
                if (remain - getStones[i] < 0) continue;
                ret = Math.max(ret, recur(remain - getStones[i], 0));
            }
        }

        dp[remain][turn] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][2];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(N, 0);

        if (ans == 0) System.out.println("SK");
        else System.out.println("CY");
    }
}