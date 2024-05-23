import java.io.*;
import java.util.*;

// 20분
public class Main {
    /*
        문제
        1분마다 한 집 또는 두 집 앞의 눈을 각각 1만큼 치울 수 있음
        모든 집 앞 눈을 전부 치우는데 걸리는 최소 시간은?

        입력
        집의 수를 의미하는 정수 N(1 <= N <= 100)
        각각 집 앞에 쌓여 있는 눈의 양 ai (1 <= ai <= 2000)

        출력
        모든 집 앞 눈을 치우는데 최소 몇 분?(24시간(1440분) 넘는다면 -1출력)
    */
    static int N, M;
    static int[] arr;
    static int[][] dp;

    static int recur(int cur, int remain) {

        if (remain < -1) return 1 << 30;

        if (cur == N) return 0;

        if (dp[cur][remain + 1] != 1 << 30) return dp[cur][remain + 1];

        int ret = 1 << 30;

        // 현재 행에 적는경우
        ret = Math.min(ret, recur(cur + 1, remain - arr[cur] - 1));

        // 현재 행에 적지 않는 경우
        ret = Math.min(ret, recur(cur + 1, M - arr[cur] - 1) + (int) Math.pow(remain + 1, 2));

        dp[cur][remain + 1] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[N + 1][1100];
        for (int[] d : dp) {
            Arrays.fill(d, 1 << 30);
        }

        System.out.println(recur(0, M));

        br.close();
    }
}