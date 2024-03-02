import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int startIdx = (int)Math.round(N * 0.15);
        int endIdx = N - (int) Math.round(N * 0.15);

        int sum = 0;
        for (int i = startIdx; i < endIdx; i++) {
            sum += arr[i];
        }

        bw.write(String.valueOf(Math.round((double)sum / (endIdx - startIdx))));
        bw.flush();
        bw.close();
        br.close();
    }
}