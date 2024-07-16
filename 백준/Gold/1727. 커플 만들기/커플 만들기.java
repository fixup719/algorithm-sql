import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[] boys;
    static int[] girls;
    static int[][] dp;

    static int recur(int b, int g) {
        // cur ; 현재 가리키는 남학우
        if (g == M) return 0;
        if (b == N) return 1 << 30;

        if (dp[b][g] != -1) return dp[b][g];

        int ret = Math.min(recur(b + 1, g + 1) + Math.abs(boys[b] - girls[g]),
                recur(b + 1, g));

        dp[b][g] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N >= M) {
            boys = new int[N];
            girls = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                boys[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(boys);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                girls[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(girls);
        } else {
            int temp = N;
            N = M;
            M = temp;

            boys = new int[N];
            girls = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                girls[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(girls);

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                boys[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(boys);
        }

        dp = new int[1010][1010];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(0, 0);

        System.out.println(ans);
    }
}