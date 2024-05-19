import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] pos;
    static double[][][] dp;

    static double recur(int start, int prv, int selected) {

        if (selected == (1 << (N + 1)) - 2) {
            return Math.sqrt(Math.pow(pos[prv][0] - pos[start][0], 2)
                    + Math.pow(pos[prv][1] - pos[start][1], 2));
        }

        if (dp[start][prv][selected] != -1) return dp[start][prv][selected];

        double ret = 1 << 30, dist;
        for (int i = 1; i <= N; i++) {
            if ((selected & (1 << i)) != 0) continue;

            dist = Math.sqrt(Math.pow(pos[prv][0] - pos[i][0], 2)
                    + Math.pow(pos[prv][1] - pos[i][1], 2));

            ret = Math.min(ret, recur(start, i, selected | (1 << i)) + dist);
        }

        dp[start][prv][selected] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        pos = new int[N + 1][2];

        dp = new double[N + 1][N + 1][1 << (N + 1)];
        for (double[][] d1 : dp) {
            for (double[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        double ans = 1 << 30;
        for (int i = 1; i <= N; i++) {
            ans = Math.min(ans, recur(i, i, 1 << i));
        }

        System.out.println(ans);

        bw.flush();
        bw.close();
        br.close();
    }
}