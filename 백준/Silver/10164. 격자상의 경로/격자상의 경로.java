import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K; // 번호 ; i * M + j + 1
    static int[] delR = {1, 0};
    static int[] delC = {0, 1};
    static int[][][] dp = new int[20][20][2];

    static int recur(int row, int col, int isVisited) {
        if (row == N - 1 && col == M - 1) {
            if (isVisited > 0 || K == 0) return 1;
            else return 0;
        }

        if (dp[row][col][isVisited] != -1) return dp[row][col][isVisited];

        int ret = 0, mr, mc;
        for (int dir = 0; dir < 2; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (mr < 0 || mc < 0 || N <= mr || M <= mc) continue;
            if (mr * M + mc + 1 == K) {
                ret += recur(mr, mc, 1);
            } else {
                ret += recur(mr, mc, isVisited);
            }
        }

        dp[row][col][isVisited] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        int ret = recur(0, 0, 0);
        System.out.println(ret);

    }
}

