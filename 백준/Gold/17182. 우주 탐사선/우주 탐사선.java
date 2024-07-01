import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] arr;
    static int[][][][] dp;

    static int recur(int cur, int cnt, int depth, int visited) {

        if (depth > 2 * cnt) return 1 << 30;

        if (cnt == N) return 0;

        if (dp[cur][cnt][depth][visited] != -1) return dp[cur][cnt][depth][visited];

        int ret = 1 << 30;
        for (int i = 0; i < N; i++) {
            if (i == cur) continue;

            if ((visited & (1 << i)) != 0) ret = Math.min(ret, recur(i, cnt, depth + 1, visited) + arr[cur][i]);
            else {
                ret = Math.min(ret, recur(i, cnt + 1, depth + 1, visited | (1 << i)) + arr[cur][i]);
            }
        }

        dp[cur][cnt][depth][visited] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[20][20][21][1 << 10];
        for (int[][][] d1 : dp) {
            for (int[][] d2 : d1) {
                for (int[] d3 : d2) {
                    Arrays.fill(d3, -1);
                }
            }
        }

        System.out.println(recur(K, 1, 1, 1 << K));
    }
}
