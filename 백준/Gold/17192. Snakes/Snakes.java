import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] arr;
    static int[][] dp;

    static int recur(int cur, int kRemain) {
        if (cur == N) return 0;
        if (kRemain < 0) return Integer.MAX_VALUE / 2;
        if (dp[cur][kRemain] != -1) return dp[cur][kRemain];

        int ret = Integer.MAX_VALUE / 2;
        int maxSize = 0;
        int sum = 0;

        for (int i = cur; i < N; i++) {
            maxSize = Math.max(maxSize, arr[i]);
            sum += arr[i];
            int waste = (i - cur + 1) * maxSize - sum;
            ret = Math.min(ret, recur(i + 1, kRemain - 1) + waste);
        }

        dp[cur][kRemain] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        int result = recur(0, K);
        System.out.println(result);

        br.close();
    }
}
