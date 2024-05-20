import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[][] arr;
    static int[][] dp;

    static int recur(int cur, int selected) {

        if (selected == (1 << 5) - 1 || cur == N) return 0;

        if (dp[cur][selected] != -1) return dp[cur][selected];

        int ret = 0;
        for (int i = 0; i < 5; i++) {
            if ((selected & 1 << i) != 0) continue;

            // 현재 후보 선택
            ret = Math.max(recur(cur + 1, selected | 1 << i) + arr[i][cur], ret);

            // 현재 후보 선택 X
            ret = Math.max(ret, recur(cur + 1, selected));
        }

        dp[cur][selected] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[5][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N + 1][1 << 6];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(recur(0, 0));

        br.close();
    }
}