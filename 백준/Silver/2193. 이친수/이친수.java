import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N;
    static long[][] dp = new long[100][2];

    static long recur(int cur, int prv) {

        if (cur == N) return 1;

        if (dp[cur][prv] != -1) return dp[cur][prv];

        long ret = 0;
        // 0선택
        if (cur != 0) ret += recur(cur + 1, 0);
        // 1선택
        if (prv != 1) ret += recur(cur + 1, 1);

        dp[cur][prv] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (long[] d : dp) {
            Arrays.fill(d, -1);
        }

        long ret = recur(0, 0);
        System.out.println(ret);
    }
}

