import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;

    static int recur(int cur, int prv) {
        if (cur == N) return 0;

        if (dp[cur][prv] != -1) return dp[cur][prv];

        int ret = Integer.MIN_VALUE;

        if (prv < arr[cur]) ret = Math.max(ret, recur(cur + 1, arr[cur]) + arr[cur]);

        ret = Math.max(ret, recur(cur + 1, prv));

        dp[cur][prv] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[1010][100010];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(0, 0);

        System.out.println(ans);
    }
}