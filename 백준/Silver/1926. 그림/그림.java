import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int nodeCnt;

    static void dfs(int cr, int cc) {
        visited[cr][cc] = true;
        nodeCnt++;

        int mr, mc;
        for (int dir = 0; dir < 4; dir++) {
            mr = cr + delR[dir];
            mc = cc + delC[dir];

            if (mr < 0 || mc < 0 || N <= mr || M <= mc || visited[mr][mc]) continue;
            if (arr[mr][mc] == 0) continue;

            dfs(mr, mc);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M];
        int cnt = 0, max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;
                if (arr[i][j] == 0) continue;

                nodeCnt = 0;
                dfs(i, j);
                max = Math.max(max, nodeCnt);
                cnt++;
            }
        }

        System.out.println(cnt);
        System.out.println(max);
    }
}