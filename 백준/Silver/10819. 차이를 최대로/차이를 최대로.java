import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N; // 배열 크기
    static int[] arr; // 배열
    static int answer;
    static boolean[] visited;
    static int[] selected;

    static void recur(int cur) {

        if (cur == N) {
            int sum = 0;
            for (int i = 0; i < N - 1; i++) {
                sum += Math.abs(selected[i] - selected[i + 1]);
            }
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            selected[cur] = arr[i];
            recur(cur + 1);
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[N];
        selected = new int[N];
        recur(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}