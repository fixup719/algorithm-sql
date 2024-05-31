import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] arr;
    static int[][][] dp;

    static int recur(int cur, int end, int selected) {

        if (cur == N) return 0;

        if (dp[cur][end][selected] != -1) return dp[cur][end][selected];

        int ret = 0;
        for (int i = 0; i < N; i++) {
            if ((selected & (1 << i)) != 0) continue;

            if (cur != 0 && end != arr[i].charAt(0) - 'A') continue;

            ret = Math.max(ret, recur(cur + 1, arr[i].charAt(arr[i].length() - 1) - 'A', selected | (1 << i)) + arr[i].length());
        }

        recur(cur + 1, end, selected);

        dp[cur][end][selected] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        dp = new int[20][30][1 << 16];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        System.out.println( recur(0, 0, 0));

        br.close();
    }
}