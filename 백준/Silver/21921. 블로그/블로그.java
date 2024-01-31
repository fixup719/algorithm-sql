import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] visits = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            visits[i] = visits[i - 1] + Integer.parseInt(st.nextToken());
        }

        int cnt = 0, nop = 0, max = 0;
        for (int i = X; i <= N ; i++) {
            nop = visits[i] - visits[i - X];
            if (max < nop) {
                max = nop;
                cnt = 1;
            } else if (max == nop) {
                cnt++;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (max == 0) {
            sb.append("SAD");
        } else {
            sb.append(max + "\n" + cnt);
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}