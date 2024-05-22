import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[][] arr;
    static int[][][] dp;

    static int recur(int prv, int selected, int prvPrice) {

        if (dp[prv][selected][prvPrice] != -1) return dp[prv][selected][prvPrice];

        int ret = 0;
        for (int i = 0; i < N; i++) {
            if ((selected & 1 << i) != 0) continue;
            if (prvPrice > arr[prv][i]) continue;

            ret = Math.max(recur(i, selected | 1 << i, arr[prv][i]) + 1, ret);
        }

        dp[prv][selected][prvPrice] = ret;
        return ret;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = input.charAt(j) - '0';
            }
        }

        dp = new int[N + 1][1 << (N + 1)][10];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

//        System.out.println(1<<15);
        System.out.println(recur(0,1, 0) + 1);

        br.close();
    }
}