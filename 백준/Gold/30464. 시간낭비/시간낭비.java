import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;

    static int recur(int cur, int reverse) {

        if (cur < 0 || N <= cur) return Integer.MIN_VALUE;

        if (cur == N - 1) return 0;

        if (arr[cur] == 0) return Integer.MIN_VALUE;

        if (dp[cur][reverse] != -1) return dp[cur][reverse];

        int ret = Integer.MIN_VALUE;
        if (reverse == 0) {
            // 정방향으로 이동
            // 또는 방향 바꾸고 반대 방향 이동
            ret = Math.max(recur(cur - arr[cur], reverse + 1) + 1
                    , recur(cur + arr[cur], reverse) + 1);
        } else if (reverse == 1) {
            // 반대 방향으로 이동
            // 또는 방향 바꾸고 정방향 이동
            ret = Math.max(recur(cur + arr[cur], reverse + 1) + 1
                    , recur(cur - arr[cur], reverse) + 1);
        } else {
            // 정방향 이동
            ret = Math.max(ret, recur(cur + arr[cur], reverse) + 1);
        }

        dp[cur][reverse] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 10][3];
        for (int[] d1 : dp) {
            Arrays.fill(d1, -1);
        }

        int ans = recur(0, 0);

        if (ans < 0) System.out.println("-1");
        else System.out.println(ans);
    }
}
