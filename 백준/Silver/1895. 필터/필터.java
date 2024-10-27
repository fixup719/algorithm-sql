import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[][] arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int T = Integer.parseInt(br.readLine());
        int[] filtering = new int[10];
        int idx = 0, ans = 0;
        for (int i = 0; i < R - 2; i++) {
            for (int j = 0; j < C - 2; j++) {
                idx = 0;
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        filtering[idx++] = arr[i + r][j + c];
                    }
                }
                Arrays.sort(filtering);
                if (filtering[4] >= T) ans++;
            }

        }

        System.out.println(ans);
    }
}

