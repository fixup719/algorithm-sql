import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[] polls = new int[1010];
        int x, y;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            polls[x] = y;
        }

        int[] prefix = new int[1010];
        for (int i = 1; i < 1010; i++) {
            prefix[i] = Math.max(prefix[i - 1], polls[i]);
        }

        int[] suffix = new int[1010];
        for (int i = 1008; i >= 1; i--) {
            suffix[i] = Math.max(suffix[i + 1], polls[i]);
        }

        int sum = 0;
        for (int i = 0; i < 1010; i++) {
            sum += Math.min(prefix[i], suffix[i]);
        }

        bw.write(String.valueOf(sum));
        bw.flush();
        bw.close();
        br.close();
    }
}