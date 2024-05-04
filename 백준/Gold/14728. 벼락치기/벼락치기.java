import java.io.*;
import java.util.*;

public class Main {
    static int N, T;
    static int[] time;
    static int[] score;
    static int[][] dp;

    static int recur(int cur, int totalTime) {
        if (totalTime > T) return -1000000000;

        if (cur == N) return 0;

        if (dp[cur][totalTime] != -1) return dp[cur][totalTime];

        int ret = 0;

        ret = Math.max(ret, recur(cur + 1, totalTime));
        ret = Math.max(ret, recur(cur + 1, totalTime + time[cur]) + score[cur]);

        dp[cur][totalTime] = ret;
        return dp[cur][totalTime];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        time = new int[N];

        score = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            score[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[110][100010];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        System.out.println(recur(0,0));

        br.close();
    }
}