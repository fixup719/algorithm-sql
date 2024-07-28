import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp;

    static int recur(int cur, int prv) {
        if (cur == N) return 0;

        if (dp[cur][prv] != -1) return dp[cur][prv];

        int ret = 0;

        // 현재 상자 넣기
        if (arr[cur] > prv) ret = Math.max(ret, recur(cur + 1, arr[cur]) + 1);

        // 안 넣기
        ret = Math.max(ret, recur(cur + 1, prv));

        dp[cur][prv] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[1010][1010];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(0, 0);

        System.out.println(ans);
    }
}