import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][] dp = new int[2001][2001];


    // 10 20 3 6 1
    // 5 12 6 10 1
    static int recur(int start, int end, int cur) {

        if (cur == N + 1) return 0;

        if (dp[start][end] != -1) return dp[start][end];

        int ret = 0;

        // 가장 왼쪽 선택
        ret = Math.max(ret, recur(start + 1, end, cur + 1) + arr[start] * cur);

        // 가장 오른쪽 선택
        ret = Math.max(ret, recur(start, end - 1, cur + 1) + arr[end] * cur);

        dp[start][end] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int[] d1 : dp) {
            Arrays.fill(d1, -1);
        }

        int ret = recur(0, N - 1, 1);

        System.out.println(ret);
    }
}

