import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static final int MOD = 1000000000;
    static int[][] dp;

    static int backtracking(int cnt, int total) {
        // cur : 현재 정수
        // cnt : 더한 정수 개수
        // total : 정수 총 합

        // 더한 정수 개수가 K보다 큰 경우 OR 정수 총 합이 N을 넘어서는 경우
        // 답이 될 수 없으므로 return 0
        if (cnt > K || total > N) return 0;

        // 답이 되므로 return 1
        if (total == N) return 1;

        if (dp[cnt][total] != -1) return dp[cnt][total];

        int ret = 0;
        for (int i = 0; i <= N; i++) {
            ret = (ret + backtracking(cnt + 1, total + i)) % MOD;
        }

        dp[cnt][total] = ret;
        return dp[cnt][total];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 10][40010];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        bw.write(String.valueOf(backtracking(0, 0) % MOD));
        bw.flush();
        bw.close();
        br.close();
    }
}