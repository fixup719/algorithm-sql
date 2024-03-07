import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][][][] dp;

    static long recur(int cur, int nearCnt, int nearTwoCnt, int two) {

        if (nearCnt > 2 || nearTwoCnt >= 2) return 0;

        if (cur == N) {
            if (two == 1) return 1;
            else return 0;
        }

        if (dp[cur][nearCnt][nearTwoCnt][two] != -1) return dp[cur][nearCnt][nearTwoCnt][two];

        dp[cur][nearCnt][nearTwoCnt][two] = (int)((recur(cur + 1, 0, 0, two)
                + recur(cur + 1, nearCnt + 1, 0, two)
                + recur(cur + 1, nearCnt + 1, nearTwoCnt + 1, 1)) % 1000000007);

        return dp[cur][nearCnt][nearTwoCnt][two];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        dp = new int[N][3][2][2];

        for (int[][][] el1 : dp) {
            for (int[][] el2 : el1) {
                for (int[] el3 : el2) {
                    Arrays.fill(el3, -1);
                }
            }
        }

        bw.write(String.valueOf(recur(1,0,0,0)));
        bw.flush();
        bw.close();
        br.close();
    }
}