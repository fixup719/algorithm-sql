import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] arr;
    static int[][] dp = new int[1010][10010];

    static int recur(int cur, int timeSum) {
        if (timeSum > N) return Integer.MIN_VALUE;

        if (cur == K) return 0;

        if (dp[cur][timeSum] != -1) return dp[cur][timeSum];

        int ret = 0;

        ret = Math.max(ret, recur(cur + 1, timeSum + arr[cur][1]) + arr[cur][0]);
        ret = Math.max(ret, recur(cur + 1, timeSum));

        dp[cur][timeSum] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[K][2];
        int imp, time;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            imp = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());

            arr[i][0] = imp;
            arr[i][1] = time;
        }

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(recur(0, 0));

    }
}
