import java.io.*;
import java.util.*;

public class Main {
    static int N, M, Q;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 누적합 만들기
        for (int j = 1; j <= M; j++) {
            for (int i = 1; i <= N; i++) {
                arr[i][j] += arr[i - 1][j];
            }
        }

        int a, b, sum;
        int[][] dp = new int[2010][2010];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            sum = 0;

            if (dp[a][b] != -1) {
                sum = dp[a][b];
            } else {
                for (int j = 0; j < a; j++) {
                    if (b - j <= 0) break;
                    sum += arr[a - j][b - j];
                }
                dp[a][b] = sum;
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
    }
}

