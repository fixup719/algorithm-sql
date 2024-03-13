import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static char[][] map;
    static int[] delR = {0, 1, 0};
    static int[] delC = {0, 0, 1};
    static int answer;

//    static int[][][] dp;

    // 1. 백트랙킹
    static void recur(int row, int col, int dir, int cnt) {

        if (cnt > K) return; // 더이상 우회할 수 없는데 우회한 경우

        if (row == N - 1 && col == N - 1) {
            answer++;
            return;
        }

        int mrow, mcol;
        for (int i = 1; i < 3; i++) {
            mrow = row + delR[i];
            mcol = col + delC[i];

            if (mrow < 0 || mcol < 0 || N <= mrow || N <= mcol || map[mrow][mcol] == 'H') continue;

            if (dir != 0 && dir != i) {
                recur(mrow, mcol, i, cnt + 1);
            } else {
                recur(mrow, mcol, i, cnt);
            }

        }
    }

    // 2. 함수 + 메모이제이션
//    static int recur(int row, int col, int dir, int cnt) {
//        if (cnt > K) return 0; // 더이상 우회할 수 없는데 우회한 경우
//
//        if (row == N - 1 && col == N - 1) return 1;
//
//        if (dp[row][col][dir] != -1) return dp[row][col][dir];
//
//        int mrow, mcol;
//        int ret = 0;
//        for (int i = 1; i < 3; i++) {
//            mrow = row + delR[i];
//            mcol = col + delC[i];
//
//            if (mrow < 0 || mcol < 0 || N <= mrow || N <= mcol || map[mrow][mcol] == 'H') continue;
//
//            if (dir != 0 && dir != i) {
//                ret += recur(mrow, mcol, i, cnt + 1);
//            } else {
//                ret += recur(mrow, mcol, i, cnt);
//            }
//        }
//
//        dp[row][col][dir] = ret;
//        return dp[row][col][dir];
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        String str;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new char[N][N];
            for (int i = 0; i < N; i++) {
                str = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = str.charAt(j);
                }
            }

//            dp = new int[N + 1][N + 1][3];
//            for (int i = 0; i < N + 1; i++) {
//                for (int j = 0; j < N + 1; j++) {
//                    Arrays.fill(dp[i][j], -1);
//                }
//            }

            answer = 0;
            recur(0, 0, 0, 0);
            sb.append(answer+ "\n");
    }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}