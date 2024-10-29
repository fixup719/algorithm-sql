import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        long ret;
        for (int tc = 1; tc <= T; tc++) {
            sb.append("Case #").append(tc).append(": ");
            N = Integer.parseInt(br.readLine());
            arr = new long[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = arr[i-1][j-1] + Integer.parseInt(st.nextToken());
                }
            }

            ret = 0;
            for (int row = 1; row <= N; row++) {
                ret = Math.max(ret, arr[row][N]);
            }
            for (int col = 1; col <= N; col++) {
                ret = Math.max(ret, arr[N][col]);
            }

            sb.append(ret).append("\n");
        }

        System.out.println(sb);
    }
}

