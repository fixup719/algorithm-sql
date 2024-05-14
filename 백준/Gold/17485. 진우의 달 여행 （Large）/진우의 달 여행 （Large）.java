import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[][] map;
    static int[] delR = {0, 1, 1, 1};
    static int[] delC = {0, -1, 0, 1};
    static int[][][] dp;
    static int answer = 1 << 30;

    static int dfs(int cr, int cc, int prvDir) {
        // 달에 도착
        if (cr == N) return 0;

        // 메모 확인
        if (dp[cr][cc][prvDir] != -1) return dp[cr][cc][prvDir];

        int ret = 1 << 30;
        int mr, mc;
        for (int i = 1; i <= 3; i++) {

            // 연속 이동 방지
            if (i == prvDir) continue;

            mr = cr + delR[i];
            mc = cc + delC[i];

            if (mc < 0 || M <= mc) continue;

            ret = Math.min(ret, dfs(mr, mc, i) + map[mr][mc]);
        }

        dp[cr][cc][prvDir] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[1010][1010][4];
        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        for (int col = 0; col < M; col++) {
            answer = Math.min(answer, dfs(0, col, 0));
        }

        System.out.println(answer);

        br.close();
    }
}