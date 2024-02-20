import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, K, cnt;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();

    static void recur(int cur, int sum) {
        if(sum > N) return;

        if (sum == N) {
            cnt++;

            if (cnt == K) {
                sb.setLength(0);
                for (int i = 0; i < cur; i++) {
                    sb.append(arr[i]);
                    if (i < cur - 1) {
                        sb.append("+");
                    }
                }
            }

            return;
        }

        for (int i = 1; i <= 3; i++) {
            arr[cur] = i;
            sum += i;
            recur(cur + 1, sum);
            arr[cur] = 0;
            sum -= i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        sb.append(-1);
        recur(0, 0);

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}