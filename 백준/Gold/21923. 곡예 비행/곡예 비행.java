import java.io.*;
import java.util.*;

public class Main {
    static int N, M; // 세로, 가로
    static int[] delR = {-1, 0, 0, 1}; // 0~1 : 상승, 2~3 : 하강
    static int[] delC = {0, 1, 1, 0};
    static int[][] arr;
    static int[][][] dp;

    static int recur(int row, int col, int change) {
        if (row == N - 1 && col == M - 1 && change == 1) return arr[N - 1][0];

        if (dp[row][col][change] != -1) return dp[row][col][change];

        int ret = Integer.MIN_VALUE, mrow, mcol;
        // 상승이라면
        if (change == 0) {
            for (int dir = 0; dir <= 1; dir++) {
                mrow = row + delR[dir];
                mcol = col + delC[dir];

                if (mrow < 0 || mcol < 0 || N <= mrow || M <= mcol) continue;

                // 상승유지
                ret = Math.max(ret, recur(mrow, mcol, change) + arr[mrow][mcol]);
            }
            // 하강으로 바꾸기
            ret = Math.max(ret, recur(row, col, 1) + arr[row][col]);
        }
        // 하강이라면
        else if (change == 1) {
            for (int dir = 2; dir <= 3; dir++) {
                mrow = row + delR[dir];
                mcol = col + delC[dir];

                if (mrow < 0 || mcol < 0 || N <= mrow || M <= mcol) continue;

                // 하강유지
                ret = Math.max(ret, recur(mrow, mcol, change) + arr[mrow][mcol]);
            }
        }

        dp[row][col][change] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        dp = new int[1010][1010][3];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ret = recur(N - 1, 0, 0);
        System.out.println(ret);
    }
}

