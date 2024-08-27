import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] arr;
    static int answer;

    static void recur(int cur, int cnt, int total) {
        if (cur == N){
            if (cnt != 0 && total == S){
                answer++;
            }
            return;
        }

        recur(cur + 1, cnt + 1, total + arr[cur]);
        recur(cur + 1, cnt, total);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        recur(0,0,0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}