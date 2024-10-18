import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[][] dp = new long[21][1 << 20];

    static long recur(int cur, int state) {
        if (cur == N) return 1;

        if (dp[cur][state] != -1) return dp[cur][state];

        long ret = 0;
        for (int i = 0; i < N; i++) {
            if (cur == i || (state & (1 << i)) > 0) continue;
            ret += recur(cur + 1, state | (1 << i));
        }

        dp[cur][state] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        long ret;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            for (long[] d1 : dp) {
                Arrays.fill(d1, -1);
            }

            ret = recur(0, 0);
            sb.append(ret).append("\n");
        }
        System.out.println(sb);
    }
}

