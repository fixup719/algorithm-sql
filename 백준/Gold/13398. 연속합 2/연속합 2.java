import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] arr;
    static int[][][][] dp;

    static int recur(int cur, int start, int end, int remain) {

        if (cur == N) {
            if (start == 0) return Integer.MIN_VALUE;
            else return 0;
        }

        if (dp[cur][start][end][remain] != -1) return dp[cur][start][end][remain];

        int ret = Integer.MIN_VALUE;

        // 현재 수 합하기 + 연속합 시작 -> start = 1
        // 연속합이 끝나지 않았다면
        if (end == 0) ret = Math.max(ret, recur(cur + 1, 1, end, remain) + arr[cur]);

        // 현재 수 건너뛰기
        if (start > 0) {
            if (remain > 0) {
                // 중간에 한 번 건너 뛸 수 있음
                ret = Math.max(ret, recur(cur + 1, start, end, remain - 1));
            } else {
                // 이미 건너 뛴 경우 -> 아예 종료
                ret = Math.max(ret, recur(cur + 1, start, 1, remain));
            }
        } else {
            // 아직 시작하지 않았다면 건너뛰기 가능
            ret = Math.max(ret, recur(cur + 1, start, end, remain));
        }

        dp[cur][start][end][remain] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[100010][2][2][2];
        for (int[][][] d : dp) {
            for (int[][] d2 : d) {
                for (int[] d3 : d2) {
                    Arrays.fill(d3, -1);
                }
            }
        }

        int ans = recur(0, 0, 0, 1);

        System.out.println(ans);
    }
}