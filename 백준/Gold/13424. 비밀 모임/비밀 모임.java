import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N, M;
        int[][] arr;
        int a, b, c, f, idx, sum, min, ans;
        int[] friend;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 방의 수
            M = Integer.parseInt(st.nextToken()); // 비밅통로 개수

            arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i != j) arr[i][j] = INF;
                }
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken()) - 1;
                b = Integer.parseInt(st.nextToken()) - 1;
                c = Integer.parseInt(st.nextToken());

                arr[a][b] = Math.min(arr[a][b], c);
                arr[b][a] = Math.min(arr[b][a], c);
            }

            f = Integer.parseInt(br.readLine());
            friend = new int[f];
            idx = 0;
            st = new StringTokenizer(br.readLine());
            while (idx < f) {
                friend[idx++] = Integer.parseInt(st.nextToken()) - 1;
            }

            for (int k = 0; k < N; k++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                    }
                }
            }

            min = 1 << 30;
            ans = 0;
            for (int i = 0; i < N; i++) {
                sum = 0;
                for (int j = 0; j < f; j++) {
                    sum += arr[i][friend[j]];
                }

                if (min > sum) {
                    min = sum;
                    ans = i;
                }
            }

            sb.append(ans + 1).append("\n");
        }

        System.out.println(sb);
    }
}