import java.io.*;
import java.util.*;

public class Main {
    /*
        1~N까지 포도주 잔이 순서대로 존재
        각 잔에는 포도주가 들어있음

        이 때, 마실 포도주 잔을 선택하면 해당 포도주는 모두 마시기
        연속으로 3잔 X

        이때 최대로 마실 수 있는 포도주 양은?

        N은 1만까지

     */
    static int N;
    static int[] arr;
    static int[][] dp;

    static int recur(int cur, int cnt) {

        if (cnt > 2) return Integer.MIN_VALUE;

        if (cur == N) return 0;

        if (dp[cur][cnt] != -1) return dp[cur][cnt];

        int ret = 0;

        // 현재 잔 선택
        ret = Math.max(ret, recur(cur + 1, cnt + 1) + arr[cur]);

        // 선택 x
        ret = Math.max(ret, recur(cur + 1, 0));

        dp[cur][cnt] = ret;
        return ret;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[10010][10];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(recur(0, 0));

        br.close();
    }
}