import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] arr;
    static int[][][] dp = new int[310][10][10];

    static int recur(int cur, int use, int pass) {
        if (cur == N){
            if (use == 0) return Integer.MIN_VALUE;
            else return 0;
        }

        if (dp[cur][use][pass] != -1) return dp[cur][use][pass];

        // 연속해서 3개이상 X
        int ret = Integer.MIN_VALUE;
        if (use < 2) {
            // 아직 연속된 계단이 1개니까 연속으로 오르기 가능
            ret = Math.max(ret, recur(cur + 1, use + 1, 0) + arr[cur]);
            // 또는 그냥 건너 뛰기
            if (pass < 1) ret = Math.max(ret, recur(cur + 1, 0, pass + 1));
        } else if (pass < 1){
            // cnt가 이미 2인 경우는 더 이상 연속해서 오를 수 없으므로 건너 뛰기
            ret = Math.max(ret, recur(cur + 1, 0, pass + 1));
        }

        dp[cur][use][pass] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int[][] d1 : dp) {
            for (int[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }
        int ret = recur(0, 0, 0);
        System.out.println(ret);
    }
}

