import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
        * N명의 아이들을 번호순서대로 배치하기 위해 옮겨야 할 최소 아이 개수
        * LIS => 최장 증가 부분 수열 => 즉 증가하는 애들만 뺴고 나머지를 이동시키면 됨
        * */

        int N = Integer.parseInt(br.readLine()); // 아이들 수

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }


        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, dp[i]);
        }

        bw.write(String.valueOf(N - max));
        bw.flush();
        bw.close();
        br.close();
    }
}