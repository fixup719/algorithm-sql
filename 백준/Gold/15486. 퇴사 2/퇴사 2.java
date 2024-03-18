import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 5000];

        for (int i = N - 1; i >= 0; i--) {
            if (i + arr[i][0] <= N) {
                dp[i] = Math.max(dp[i + arr[i][0]] + arr[i][1], dp[i + 1]);
            } else {
                dp[i] = dp[i + 1];
            }

        }

        bw.write(String.valueOf(dp[0]));
        bw.flush();
        bw.close();
        br.close();
    }
}