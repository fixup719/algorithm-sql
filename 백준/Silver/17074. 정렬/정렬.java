import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] diff = new int[N - 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (i != 0) diff[i - 1] = arr[i - 1] - arr[i];
        }

        // 1 2 2 3 2

        int idx = 0, cnt = 0;
        for (int i = 0; i < N - 1; i++) {
            if (diff[i] > 0) {
                cnt++;
                idx = i;
            }
        }

        int answer = 0;
        if (cnt == 1) {
            if (idx == 0 || idx == N - 2) answer++; // 양 끝 값은 무조건 제거할 수 있으니까

            if (0 <= idx - 1 && idx + 1 < N && arr[idx - 1] <= arr[idx + 1]) answer++;
            if (idx + 2 < N && arr[idx] <= arr[idx + 2]) answer++;
            if (N == 2) answer++;
        } else if (cnt == 0) {
            answer = N;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}