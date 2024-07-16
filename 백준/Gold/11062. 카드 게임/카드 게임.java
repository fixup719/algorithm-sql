import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] arr;
    static int[][][] dp;

    static int recur(int start, int end, int turn) {
        if (start > end) return 0;

        if (dp[start][end][turn] != -1) return dp[start][end][turn];

        int ret = 0;
        if (turn == 0) {
            ret = Math.max(recur(start, end - 1, 1) + arr[end],
                    recur(start + 1, end, 1) + arr[start]);
        } else {
            ret = Math.min(recur(start, end - 1, 0) ,
                    recur(start + 1, end, 0));
        }

        dp[start][end][turn] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dp = new int[1010][1010][2];
            for (int[][] d1 : dp) {
                for (int[] d2 : d1) {
                    Arrays.fill(d2, -1);
                }
            }

            int ans = recur(0, N - 1, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}