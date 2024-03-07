import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[][] arr;
    static int[][] dp;
    static int recur(int cur, int weight) {

        if (weight > K) return -1000000000;

        if (cur == N) return 0;

        if (dp[cur][weight] != -1) return dp[cur][weight];

        int ret = Math.max(recur(cur + 1, weight + arr[cur][0]) + arr[cur][1], recur(cur + 1, weight));
        dp[cur][weight] = ret;

        return dp[cur][weight];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][100010];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        bw.write(String.valueOf(recur(0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }
}