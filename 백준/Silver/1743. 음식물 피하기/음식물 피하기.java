import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] arr;
    static boolean[][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int cnt;

    static void dfs(int row, int col) {
        visited[row][col] = true;
        cnt++;

        int mr, mc;
        for (int dir = 0; dir < 4; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (mr < 0 || mc < 0 || N <= mr || M <= mc
                    || visited[mr][mc] || arr[mr][mc] == 0) continue;

            dfs(mr, mc);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        int a, b;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;

            arr[a][b] = 1;
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || arr[i][j] == 0) continue;

                cnt = 0;
                dfs(i, j);

                ans = Math.max(ans, cnt);
            }
        }

        System.out.println(ans);
    }
}