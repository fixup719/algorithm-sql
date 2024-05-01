import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] price;
    static int[][] dp;

    static int backtracking(int cur, int total) {
        // cur : 현재 가리키는 동전
        // total : 남은 가치 합

        // 가치를 초과하는 경우는 최댓값 반환 -> 정답으로 선택되면 안 되므로
        if (total < 0 || cur >= N) return 1 << 30;

        if (total == 0) return 0;

        if (dp[cur][total] != -1) return dp[cur][total];

        // 현재 동전만 선택 또는 동전 선택 안하고 다음 동전으로 넘어가는 경우
        int ret = Math.min(backtracking(cur, total - price[cur]) + 1,
                backtracking(cur + 1, total));

        dp[cur][total] = ret;
        return dp[cur][total];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        price = new int[N];
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[110][10010];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        int answer = backtracking(0, K);
        if (answer == 1 << 30) bw.write("-1");
        else bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}