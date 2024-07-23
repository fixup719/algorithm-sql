import java.io.*;
import java.util.*;

public class Main {
    static int N, X;
    static int[][] arr;
    static int[][] dp;

    static int recur(int cur, int sum) {
        if (sum > X) return 0;

        if (cur == N) {
            if (sum == X) return 1;
            else return 0;
        }

        if (dp[cur][sum] != -1) return dp[cur][sum];

        int ret = 0;
        // 현재 파이프에서 연결할 수 있는 개수만큼 재귀 돌리기
        // 0 ~ arr[cur][1]개
        for (int i = 0; i <= arr[cur][1]; i++) {
            ret += recur(cur + 1, sum + arr[cur][0] * i);
        }

        dp[cur][sum] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[101][10001];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        int ans = recur(0, 0);

        System.out.println(ans);
    }
}