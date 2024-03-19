import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int cnt;
    static int[][][] dp;

    // top down으로 풀어보기
//    static void recur(int cur, int prev, int total) {
//        // cnt 인접한 1 개수
//
//        if(total > K) return;
//
//        if (cur == N) {
//            if(total == K) cnt++;
//            return;
//        }
//
//        if (prev == 1) {
//            recur(cur + 1, 1, total + 1);
//        } else {
//            recur(cur + 1, 1, total);
//        }
//        recur(cur + 1, 0, total);
//
//    }

    static int recur(int cur, int prev, int total) {

        // cnt 인접한 1 개수
        if (total > K) return 0;

        if (cur == N) {
            if (total == K) return 1;
            return 0;
        }

        if (dp[cur][prev][total] != -1) return dp[cur][prev][total];

        int ret = 0;
        if (prev == 1) {
            ret += recur(cur + 1, 1, total + 1);
        } else {
            ret += recur(cur + 1, 1, total);
        }
        ret += recur(cur + 1, 0, total);

        dp[cur][prev][total] = ret;
        return dp[cur][prev][total];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            dp = new int[N + 10][2][110];
            for (int i = 0; i < N + 10; i++) {
                for (int j = 0; j < 2; j++) {
                    Arrays.fill(dp[i][j], -1);
                }
            }

            sb.append(recur(0, 0, 0) + "\n");

        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}