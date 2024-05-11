//
import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[][] arr = new int[310][310];
    static int[][] acc = new int[310][310];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2차원 누적합 배열 만들기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                acc[i][j] = acc[i][j - 1] + acc[i - 1][j] - acc[i - 1][j - 1] + arr[i][j];
            }
        }

        // K=1 ~ N 하나하나 탐색하면 최댯값 갱신
        int answer = Integer.MIN_VALUE, sum;
        for (int k = 1; k <= N; k++) {
            for (int i = k; i <= N; i++) {
                for (int j = k; j <= N; j++) {
                    sum = acc[i][j] - acc[i][j - k] - acc[i - k][j] + acc[i - k][j - k];

                    answer = Math.max(answer, sum);
                }
            }
        }

        System.out.println(answer);

        br.close();
    }
}