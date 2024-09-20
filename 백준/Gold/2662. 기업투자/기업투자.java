import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] log;
    static int[][] dp;

    static int recur(int remain, int cur) {
        // remain : 현재 갖고있는 금액
        // cur : 가리키는 회사 번호

        if (cur >= M) return 0;
        if (dp[remain][cur] != -1) return dp[remain][cur];

        int ret = 0, tmp;
        for (int i = 0; i <= remain; i++) {
            // i원 만큼 투자
            tmp = recur(remain - i, cur + 1) + arr[i][cur];
            if (ret < tmp) {
                ret = tmp;
                log[remain][cur] = i;
            }
        }
        dp[remain][cur] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N + 1][M];
        log = new int[N + 1][M];
        dp = new int[N + 1][M];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        int ret = recur(N, 0);
        sb.append(ret).append("\n");
        int sum = N;
        for (int i = 0; i < M; i++) {
            sb.append(log[sum][i]).append(" ");
            sum -= log[sum][i];
        }
        System.out.println(sb);
    }
}

