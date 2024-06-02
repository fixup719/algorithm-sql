import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr = new int[3];
    static int[][][] dp;

    static int recur(int a, int b, int c) {
        if (a <= 0) a = 0;
        if (b <= 0) b = 0;
        if (c <= 0) c = 0;

        if (a <= 0 && b <= 0 & c <= 0) return 0;

        if (dp[a][b][c] != -1) return dp[a][b][c];

        int ret = 0;
        ret = recur(a - 9, b - 3, c - 1) + 1;
        ret = Math.min(ret, recur(a - 9, b - 1, c - 3) + 1);
        ret = Math.min(ret, recur(a - 1, b - 3, c - 9) + 1);
        ret = Math.min(ret, recur(a - 1, b - 9, c - 3) + 1);
        ret = Math.min(ret, recur(a - 3, b - 9, c - 1) + 1);
        ret = Math.min(ret, recur(a - 3, b - 1, c - 9) + 1);

        dp[a][b][c] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[100][100][100];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        System.out.println(recur(arr[0],arr[1],arr[2]));
    }
}