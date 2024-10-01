import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][M + 1];
        String input;
        for (int i = 1; i < N + 1; i++) {
            input = br.readLine();
            for (int j = 1; j < M + 1; j++) {
                arr[i][j] = input.charAt(j - 1) - '0';
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < M + 1; j++) {
                arr[i][j] += arr[i][j - 1];
            }
        }

        int s, e, acc, cnt = 0;
        for (int i = 1; i < N + 1; i++) {
            s = 1;
            e = K;
            while (e <= M) {
                acc = arr[i][e] - arr[i][s - 1];
                if (acc == 0) cnt++;
                s++;
                e++;
            }
        }

        System.out.println(cnt);
    }
}

