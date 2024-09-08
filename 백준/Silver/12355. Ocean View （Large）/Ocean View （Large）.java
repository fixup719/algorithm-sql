import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp = new int[1010][1010];

    static int recur(int cur, int prv) {

        if (cur == N) return 0;

        if (dp[cur][prv] != -1) return dp[cur][prv];

        int ret = 1 << 30;
        // 현재 건물 부수기
        ret = Math.min(ret, recur(cur + 1, prv) + 1);
        // 현재 건물 안 부수기
        if (arr[cur] > prv) ret = Math.min(ret, recur(cur + 1, arr[cur]));

        dp[cur][prv] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 1; tc <= T; tc++) {
            sb.append("Case #").append(tc).append(": ");
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }
            sb.append(recur(0, 0)).append("\n");
        }

        System.out.println(sb);
    }
}

