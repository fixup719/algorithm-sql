import java.io.*;
import java.util.*;


public class Main {
    static int N, M, C;
    static int[] jewels;
    static int[][][][] dp;

    static int recur(int cur, int weight, int bagCnt, int selected) {

        if (bagCnt > M) return Integer.MIN_VALUE;

        if (cur == N) return 0;

        if (dp[cur][weight][bagCnt][selected] != -1) return dp[cur][weight][bagCnt][selected];

        int sum, ret = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {

            // 이미 선택한 보석이면 continue;
            if ((selected & 1 << i) != 0) continue;

            sum = weight + jewels[i];

            // 보석을 선택
            if (sum > C && jewels[i] <= C) {
                // 무게합을 가방 무게를 넘어가면서, 현재 보석을 다음 가방에 담을 수 있다면-> bagCnt +1
                ret = Math.max(ret, recur(cur + 1, jewels[i], bagCnt + 1, selected | 1 << i) + 1);
            } else if (sum <= C) {
                // 현재 가방에 담을 수 있다면
                ret = Math.max(ret, recur(cur + 1, sum, bagCnt, selected | 1 << i) + 1);
            }

            // 보석을 선택 X
            ret = Math.max(ret, recur(cur + 1, weight, bagCnt, selected));
        }

        dp[cur][weight][bagCnt][selected] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        jewels = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            jewels[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 1][C + 1][M + 1][1 << (N + 1)];
        for (int[][][] d1 : dp) {
            for (int[][] d2 : d1) {
                for (int[] d3 : d2) {
                    Arrays.fill(d3, -1);
                }
            }
        }

        System.out.println(recur(0, 0, 1, 0));
    }
}