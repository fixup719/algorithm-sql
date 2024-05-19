import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int[][] dp;

    static int recur(int cur, int selected) {

        if (cur == N + 1) return 0;

        if (dp[cur][selected] != -1) return dp[cur][selected];

        int ret = 1 << 30;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            ret = Math.min(ret, recur(cur + 1, selected | 1 << i) + arr[cur][i]);
            visited[i] = false;
        }

        dp[cur][selected] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); // 사람과 일의 수

        arr = new int[N + 1][N + 1];

        visited = new boolean[N + 1];

        dp = new int[N + 1][1 << (N + 1)];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(recur(1, 0));
    }
}