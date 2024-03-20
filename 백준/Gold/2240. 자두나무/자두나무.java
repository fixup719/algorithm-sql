import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T, W;
    static int[] arr;
    static int[][][] dp;


    // 탑다운으로 풀어보기,,,
    static int recur(int cur, int move, int moveCnt) {

        if (moveCnt > W) return -1000000000;

        if (cur == T) return 0;

        if (dp[cur][move][moveCnt] != -1) return dp[cur][move][moveCnt];

        int ret = 0;
        if (arr[cur] % 2 == move) {
            ret = Math.max(recur(cur + 1, (move + 1) % 2, moveCnt + 1) + 1,
                    recur(cur + 1, move, moveCnt) + 1);
        } else {
            ret = Math.max(recur(cur + 1, (move + 1) % 2, moveCnt + 1),
                    recur(cur + 1, move, moveCnt));

        }

        dp[cur][move][moveCnt] = ret;
        return dp[cur][move][moveCnt];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
         * 매초마다 1번 또는 2번 나무에서 자두가 떨어짐
         * 최대 W번만 움직일 후 있다면,
         * 먹을 수 있는 지두의 최대개수(초기위치 1)
         * */

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        arr = new int[T];
        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[T + 1][2][W + 1];
        for (int i = 0; i < T + 1; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        int max = 0;
        max = Math.max(recur(0, 1, 0), recur(0, 0, 1));

        bw.write(String.valueOf(max));

        bw.flush();
        bw.close();
        br.close();
    }
}