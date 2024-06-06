import java.io.*;
import java.util.*;

// 45분
public class Main {
    static int N, M;
    static char[][] arr;
    static int[][][] dp;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static boolean binaryCheck(int row, int col) {
        return 0 <= row && 0 <= col && row < N && col < M;
    }

    static int recur(int cur, int row, int col) {

        if (!binaryCheck(row, col) || arr[row][col] == 'H') return 0;

        if (cur == N * M * 2) return Integer.MIN_VALUE;

        if (dp[cur][row][col] != -1) return dp[cur][row][col];

        int ret = -1;
        int size = arr[row][col] - '0', mrow, mcol;
        for (int dir = 0; dir < 4; dir++) {
            mrow = row + delR[dir] * size;
            mcol = col + delC[dir] * size;
            ret = Math.max(ret, recur(cur + 1, mrow, mcol) + 1);
        }

        dp[cur][row][col] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        dp = new int[N * M * 2 + 1][N][M];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        int ans = recur(0, 0, 0);
        if (ans == N * M * 2) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

        br.close();
    }
}