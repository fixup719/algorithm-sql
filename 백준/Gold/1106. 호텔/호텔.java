import java.io.*;
import java.util.*;

public class Main {
    static int C, N;
    static int[][] arr;
    static int[][] dp;

    static int recur(int cur, int cnt) {
        if (cnt > C * 2 + 100) return 1 << 30;

        if (cur == N) {
            if (cnt >= C) return 0;
            else return 1 << 30;
        }

        if (dp[cur][cnt] != -1) return dp[cur][cnt];

        int ret = 1 << 30;

        // 현재 고객 선택X
        ret = Math.min(ret, recur(cur + 1, cnt));
        // 현재 고객 선택 후 넘기기X
        ret = Math.min(ret, recur(cur, cnt + arr[cur][1]) + arr[cur][0]);
        // 현재 고객 선택 후 넘기기O
        ret = Math.min(ret, recur(cur + 1, cnt + arr[cur][1]) + arr[cur][0]);

        dp[cur][cnt] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()); // 비용
            arr[i][1] = Integer.parseInt(st.nextToken()); // 확보 고객 수
        }

        dp = new int[N + 10][C * 2 + 110];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(0, 0);
        System.out.println(ans);
    }
}