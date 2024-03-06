import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int N, K, s, e, sum, minSum, cnt;
        int[] arr;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            s = 0;
            e = N - 1;
            minSum = arr[s] + arr[e];
            cnt = 0;
            while (s < e) {
                sum = arr[s] + arr[e];

                if (Math.abs(minSum - K) > Math.abs(sum - K)) {
                    minSum = sum;
                    cnt = 1;
                } else if (Math.abs(minSum - K) == Math.abs(sum - K)) {
                    cnt++;
                }

                if (sum < K) {
                    s++;
                } else {
                    e--;
                }
            }

            sb.append( cnt+"\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}