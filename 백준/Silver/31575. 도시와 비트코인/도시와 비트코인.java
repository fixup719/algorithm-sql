import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[] delR = {1, 0};
    static int[] delC = {0, 1};
    static int[][] dp = new int[310][310];

    static int dfs(int row, int col) {
        if (row == N - 1 && col == M - 1) return 1;
        if (dp[row][col] != -1) return dp[row][col];

        int ret = 0, mr, mc;
        for (int dir = 0; dir < 2; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (mr < 0 || mc < 0 || N <= mr || M <= mc || arr[mr][mc] == 0) continue;

            ret = Math.max(ret, dfs(mr, mc));
        }

        dp[row][col] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ret = dfs(0, 0);
        if (ret > 0) System.out.println("Yes");
        else System.out.println("No");
    }
}

