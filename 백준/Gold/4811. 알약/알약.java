import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[][] dp = new long[100][100];

    static long recur(int wCnt, int bCnt) {
        if (wCnt < bCnt || wCnt > N) return 0;

        if (wCnt + bCnt == 2 * N) return 1;

        if (dp[wCnt][bCnt] != -1) return dp[wCnt][bCnt];

        long ret = 0;
        ret += recur(wCnt + 1, bCnt);
        ret += recur(wCnt, bCnt + 1);

        dp[wCnt][bCnt] = ret;
        return dp[wCnt][bCnt];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            for (long[] ints : dp) {
                Arrays.fill(ints, -1);
            }

            sb.append(recur(0, 0)).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}