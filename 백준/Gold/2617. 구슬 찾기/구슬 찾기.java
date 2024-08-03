import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) arr[i][j] = INF;
            }
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;

            // a가 b보다 무겁다
            arr[a][b] = 1;
        }

        // 플로이드워셜
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }

        int more, less, ans = 0;
        for (int i = 0; i < N; i++) {
            more = 0;
            less = 0;
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == INF && arr[j][i] != INF) less++;
                else if (arr[i][j] != INF && arr[j][i] == INF) more++;
            }

            if (more > N / 2 || less > N / 2) ans++;
        }

        System.out.println(ans);
    }
}