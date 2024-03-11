import java.io.*;
import java.util.*;

public class Main {
    static int M, N;
    static int[][] arr;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int[][] dp;
//    static int answer;

    // 1. 백트랙킹으로 풀기
//    static void recur(int row, int col, int height) {
//
//        if (height <= arr[row][col]) return;
//
//        if (row == M - 1 && col == N - 1) {
//            answer++;
//            return;
//        }
//
//        int mrow, mcol;
//        for (int i = 0; i < 4; i++) {
//            mrow = row + delR[i];
//            mcol = col + delC[i];
//
//            if (mrow < 0 || mcol < 0 || M <= mrow || N <= mcol) continue;
//
//            recur(mrow, mcol, arr[row][col]);
//        }
//
//    }

    // 2. 함수 + 메모이제이션 적용
    static int recur(int row, int col, int height) {

        if (height <= arr[row][col]) return 0;

        if (row == M - 1 && col == N - 1) return 1;

        if (dp[row][col] != -1) return dp[row][col];

        int mrow, mcol;
        int ret = 0;
        for (int i = 0; i < 4; i++) {
            mrow = row + delR[i];
            mcol = col + delC[i];

            if (mrow < 0 || mcol < 0 || M <= mrow || N <= mcol) continue;

            ret += recur(mrow, mcol, arr[row][col]);
        }

        dp[row][col] = ret;
        return dp[row][col];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[M + 1][N + 1];

        for (int i = 0; i < M + 1; i++) {
            Arrays.fill(dp[i], -1);
        }

//        recur(0,0, 10010);

//        bw.write(String.valueOf(answer));
        bw.write(String.valueOf(recur(0,0,10001)));
        bw.flush();
        bw.close();
        br.close();
    }
}