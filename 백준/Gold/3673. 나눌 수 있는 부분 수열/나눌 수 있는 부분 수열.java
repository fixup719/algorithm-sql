import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int c = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int d, n;
        long cnt;
        long[] arr, remain;
        while (c-- > 0) {
            st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            arr = new long[n + 1];
            remain = new long[d + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                arr[i] = (arr[i-1] + Integer.parseInt(st.nextToken())) % d;
                remain[(int)arr[i]]++;
            }

            cnt = 0;
            for (int i = 0; i < d ; i++) {
                if(remain[i] >= 2) cnt += (remain[i] * (remain[i] - 1)) / 2;
                if(i == 0) cnt += remain[0];
            }

            sb.append(cnt+"\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}