import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] broken = new int[N];
        for (int i = 0; i < B; i++) {
            broken[Integer.parseInt(br.readLine())-1]++;
        }

        for (int i = 1; i < N; i++) {
            broken[i] += broken[i - 1];
        }

        int s = 1, e = s + (K-1), answer = broken[K-1];
        while (e < N) {
            answer = Math.min(answer, broken[e] - broken[s - 1]);
            s++;
            e++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}