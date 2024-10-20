import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp = new int[5001][5001];

    static int recur(int cur, int pre) {
        if (cur == N + 1) {
            return 0;
        }

        if (dp[cur][pre] != -1) return dp[cur][pre];

        int ret = 0;
        // 현재 박스 포장
        if (arr[pre] < arr[cur]) ret = Math.max(ret, recur(cur + 1, cur) + 1);
        // 포장X
        ret = Math.max(ret, recur(cur + 1, pre));

        dp[cur][pre] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int[] d1 : dp) {
            Arrays.fill(d1, -1);
        }

        int ret = recur(0, 0);
        System.out.println(ret);
    }
}

