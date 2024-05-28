import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][][] dp;

    static int recur(int order, int start, int end) {
        // order 0 -> bessie(a)
        // order 1 -> bonnie(b)

        if (start > end) return 0;

        if (dp[order][start][end] != -1) return dp[order][start][end];

        // 각 플레이어는 매 턴에서 최선의 선택을 하게 된다....

        int ret = 0;

        if (order == 0) {
            // A차례
            ret = Math.max(recur(1, start, end - 1) + arr[end], recur(1, start + 1, end) + arr[start]);
        } else {
            // B차례
            ret = Math.min(recur( 0, start, end - 1), recur(0, start + 1, end));
        }

        dp[order][start][end] = ret;
        return ret;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[2][5010][5010];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        System.out.println(recur(0, 0, N - 1));

        br.close();
    }
}