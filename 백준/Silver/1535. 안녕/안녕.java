import java.io.*;
import java.util.*;


/*
   사람의 수 N
   각각의 사람에게 인사 시 잃는 체력 1번~N번
   각각의 사람에게 인사 시 얻는 기쁨 1번~N번
   이때 체력과 기쁨은 100이하 자연수 또는 0

   얻을 수 있는 최대 기쁨은???
   초기 쳬력 100, 기쁨 0
   체력이 0이하되면 아무 기쁨 못 얻읃
 */

public class Main {
    static int N;
    static int[] minus;
    static int[] happy;
    static int[][] dp;

    static int recur(int cur, int remain) {
        if (remain <= 0) return Integer.MIN_VALUE;

        if (cur == N) return 0;

        if (dp[cur][remain] != -1) return dp[cur][remain];

        int ret = Integer.MIN_VALUE;
        // 현재 선택
        ret = Math.max(ret, recur(cur + 1, remain - minus[cur]) + happy[cur]);
        // 현재 선택 X
        ret = Math.max(ret, recur(cur + 1, remain));

        dp[cur][remain] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        minus = new int[N];
        for (int i = 0; i < N; i++) {
            minus[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        happy = new int[N];
        for (int i = 0; i < N; i++) {
            happy[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N + 10][110];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }


        System.out.println(recur(0, 100));

    }
}