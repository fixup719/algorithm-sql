import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] blocks = new int[W + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefix = new int[W + 2];
        int[] suffix = new int[W + 2];
        for (int i = 1; i <= W; i++) {
            prefix[i] = Math.max(prefix[i - 1], blocks[i]);
        }
        for (int i = W; i >= 1; i--) {
            suffix[i] = Math.max(suffix[i + 1], blocks[i]);
        }

        int sum = 0, diff = 0;
        for (int i = 1; i <= W; i++) {
            diff = Math.min(prefix[i], suffix[i]) - blocks[i];
            if (diff > 0) {
                sum += diff;
            }
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}