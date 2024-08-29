import java.io.*;
import java.util.*;

public class Main {
    static int C, V, L;
    static final int MOD = 1_000_000_007;
    static int[][] dp = new int[16][2];

    static int recur(int cur, int flag) {

        if (cur == L) {
            if (flag == 0) return 1;
            else return 0;
        }

        if (dp[cur][flag] != -1) return dp[cur][flag];

        int ret = 0;
        if (flag == 1) {
            for (int i = 1; i <= V; i++) {
                // 이전에 자음 사용 했다면 -> 반드시 모음 사용
                ret = (ret + recur(cur + 1, 0) % MOD) % MOD;
            }
        } else {
            for (int i = 1; i <= V; i++) {
                // 모음 사용
                ret = (ret + recur(cur + 1, 0) % MOD) % MOD;
            }
            for (int i = 1; i <= C; i++) {
                // 자음 사용
                ret = (ret + recur(cur + 1, 1) % MOD) % MOD;
            }
        }

        dp[cur][flag] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int ret;
        for (int tc = 1; tc <= T; tc++) {
            sb.append("Case #").append(tc).append(": ");
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }
            ret = recur(0, 0);
            sb.append(ret).append("\n");
        }
        System.out.println(sb);
    }
}

