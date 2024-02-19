import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] arr;
    static int[] selected;
    static int size, cnt;

    static void recur(int cur, int start) {
        if (cur == size) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += arr[selected[i]];
            }
            if (sum == S) {
                cnt++;
            }
            return;
        }

        for (int i = start; i <= N; i++) {
            selected[cur] = i;
            recur(cur + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        selected = new int[N];
        for (int i = 1; i <= N; i++) {
            // 크기가 1~N개인 경우
            size = i;
            recur(0, 1);
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}