import java.io.*;
import java.util.Arrays;

public class Main {
    static int N;
    static int[] dp;
/*
     1. 백트랙킹으로 구현하기
    static void recur(int remain, int cnt) {

        if (remain == 0) {
            answer = Math.min(answer, cnt);
            return;
        }

        for (int i = (int)Math.sqrt(remain); i >= 1; i--) {
            remain -= i*i;
            recur(remain, cnt + 1);

            remain += i * i;
        }

    }
 */

    // 2. 함수  + 메모이제이션 적용하기 => 현재 남은 숫자에서 제곱수의 합으로 나타낼 수 있는 가장 최소 항을 반환
    static int recur( int remain) {

        if (remain == 0) return 0;

        if (dp[remain] != -1) return dp[remain];

        int ret = 1 << 30;
        for (int i = (int)Math.sqrt(remain); i >= 1; i--) {

            ret = Math.min(ret, recur(remain - i * i) + 1);
            dp[remain] = ret;
        }

        return dp[remain];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        dp = new int[N + 50];
        Arrays.fill(dp, -1);

        bw.write(String.valueOf(recur(N)));
        bw.flush();
        bw.close();
        br.close();
    }
}