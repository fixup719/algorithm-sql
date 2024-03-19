import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 10];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N + 10];
        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];
        // max 값이 누적된다...?

//        dp[3] = Math.max(dp[2] + arr[3], arr[1] + arr[3]);
        // 근데 여기서 dp[2]+arr[3]이면은 4번째 계단 못 밟음..
        /*
            연속 3개 계단을 밟을 수 없음
            4번째 계단을 밟는다면,
            1->3->4
            1->2->4
            까지 가능하다
            2->3->4 는 불가능

            dp[N-3] + arr[N-1] + arr[N]
            dp[N-2] + arr[N]
        **/

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i]);
        }

        bw.write(String.valueOf(dp[N]));
        bw.flush();
        bw.close();
        br.close();
    }
}