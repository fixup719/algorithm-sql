import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static int[][] queries;
    static int[][] dp;

    static int recur(int s, int e) {

        if (s >= e) return 1;

        if (dp[s][e] != -1) return dp[s][e];

        if (arr[s] != arr[e]) return 0;

        int ret = recur(s + 1, e - 1);

        dp[s][e] = ret;
        return dp[s][e];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        queries = new int[M][2];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken());
            queries[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[2010][2010];
        for (int i = 0; i < 2010; i++) {
            Arrays.fill(dp[i], -1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(recur(queries[i][0], queries[i][1])+"\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}