import java.io.*;
import java.util.*;

/*
A : 강아지 A 마리 생성
B : 강아지 B마리 생성

그러나 생성된 강아지 개수가 M개 닫힌 구간중 하나 이상에 포함되면
모든 강아지가 사라짐

2가지 마법을 사용하여 최소 행동 횟수로
집에 정확히 N마리 강아지를 만드는 법은??
 */

public class Main {
    static int N, M, A, B;
    static boolean[] check = new boolean[100010];
    static int[] dp = new int[100010];

    static int recur(int sum) {
        if (sum > N) return 1 << 30;
        if (check[sum]) return 1 << 30;

        if (sum == N) return 0;

        if (dp[sum] != -1) return dp[sum];

        int ret = 1 << 30;
        // A마법 사용
        ret = Math.min(ret, recur(sum + A) + 1);
        // B마법 사용
        ret = Math.min(ret, recur(sum + B) + 1);

        dp[sum] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        int s, e;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());

            for (int j = s; j <= e; j++) {
                check[j] = true;
            }
        }

        Arrays.fill(dp, -1);

        int ans = recur(0);

        if (ans == 1 << 30) System.out.println(-1);
        else System.out.println(ans);


    }
}