import java.io.*;
import java.util.*;

public class Main {
    // N가지 동전으로 금액 M을 만드는 모든 방법의 수를 한 줄에 하나씩 출력
    static int N, M;
    static int[] coins;
    static int[][] dp = new int[30][10010];

    static int recur(int cur, int sum) {
        if (sum > M) return 0;

        if (cur == N) {
            if (sum == M) return 1;
            return 0;
        }

        if (dp[cur][sum] != -1) return dp[cur][sum];

        int ret = 0;

        for (int i = 0; i * coins[cur] <= M ; i++) {
            ret += recur(cur + 1, sum + coins[cur] * i);
        }

        dp[cur][sum] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        int ans;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            coins = new int[N];
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());

            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }
            ans = recur(0, 0);

            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }
}