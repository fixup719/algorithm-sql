import java.io.*;
import java.util.*;


/*
   (1,1)부터 (N,M)까지 자우너 탐색
   오른쪽, 아래로 한 칸 이동 가능
   자원이 있으면 채취 -> 얻을 수 있는 최대 숫자 구하기
 */

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] dp;

    static int recur(int row, int col) {

        if (row == N - 1 && col == M - 1) return 0;

        if (dp[row][col] != -1) return dp[row][col];

        int ret = 0;
        // 아래로 이동
        if (row + 1 < N) ret = Math.max(ret, recur(row + 1, col) + arr[row + 1][col]);
        // 오른쪽 이동
        if (col + 1 < M) ret = Math.max(ret, recur(row, col + 1) + arr[row][col + 1]);

        dp[row][col] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N + 10][M + 10];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(recur(0, 0) + arr[0][0]);
    }
}