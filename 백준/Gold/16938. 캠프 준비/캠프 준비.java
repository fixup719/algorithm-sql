import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, L, R, X;
    static int[] arr;
    static int size;
    static int[] selected;
    static int answer;
    static void recur(int cur, int start, int sum) {

        if (sum > R) return;

        if (cur == size) {
            if (sum < L) return;

            int max = 1, min = 1000000;

            for (int i = 0; i < size; i++) {
                if(max < arr[selected[i]]) max = arr[selected[i]];
                if(min > arr[selected[i]]) min = arr[selected[i]];
            }

            if(max - min < X) return;

            answer++;

            return;
        }

        for (int i = start; i <= N; i++) {
            selected[cur] = i;
            sum += arr[selected[cur]];
            recur(cur + 1, i + 1, sum);
            sum -= arr[selected[cur]];
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 문제 개수
        L = Integer.parseInt(st.nextToken()); // 문제 난이도 합 제한 최솟값
        R = Integer.parseInt(st.nextToken()); // 문제 난이도 합 제한 최댓값
        X = Integer.parseInt(st.nextToken()); // 문제 난이도 최대-최소 기준

        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1]; // 문제 난이도
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        selected = new int[N];
        for (int i = 2; i <= N; i++) {
            size = i;
            recur(0, 1, 0);
        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}