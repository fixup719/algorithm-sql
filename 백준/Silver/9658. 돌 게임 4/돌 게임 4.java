import java.io.*;
import java.util.*;

public class Main {
    static int[] getStones = {1, 3, 4};
    static int[][] dp;

    // 가장 마지막 돌 가져가는 사람이 짐
    static int recur(int remain, int order) {
        if (remain == 0) return order;

        if (dp[remain][order] != -1) return dp[remain][order];

        int ret;
        if (order == 0) {
            ret = 1;
            for (int i = 0; i < 3; i++) {
                if (remain < getStones[i]) continue;
                ret = Math.min(ret, recur(remain - getStones[i], 1));
            }
        } else {
            ret = 0;
            for (int i = 0; i < 3; i++) {
                if (remain < getStones[i]) continue;
                ret = Math.max(ret, recur(remain - getStones[i], 0));
            }
        }

        dp[remain][order] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 10][2];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(N, 0);

        if (ans == 0) System.out.println("SK");
        else System.out.println("CY");

        br.close();
    }
}