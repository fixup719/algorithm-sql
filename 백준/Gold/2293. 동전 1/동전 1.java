import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
         * 동전 n개
         * 가치 K
         *
         * 동전들을 적절히 사용해서 가치 k를 만드는 경우의 수 구하기(각 동전은 몇 개라도 사용 가능)
         * */

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 10]; // dp[i] : 가치 i가 되기 위한 경우의 수 개수
         /*
        1 : 1 => 1
        2 : 11 / 2 => 2
        3 : 111 / 12/ 3 => 3
        4 : 1111 / 112 / 13 / 22 / 4 => 5
        5 : 11111 / 1112 / 113 / 122 / 14 / 23 / 5 => 7
        6 : 111111 / 11112 / 1113 / 1122 / 114 / 123 / 15 / 222 / 24 / 33 / 6
        * */
        
        dp[0] = 1;

        for (int i = 0; i < N; i++) {
            for (int j = arr[i]; j <= K; j++) {
                dp[j] = dp[j] + dp[j - arr[i]];
            }
        }

        bw.write(String.valueOf(dp[K]));
        bw.flush();
        bw.close();
        br.close();
    }
}