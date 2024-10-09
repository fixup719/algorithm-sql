import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

//    static void dfs(int row, int col) {
//        visited[row][col] = true;
//
//        int mr, mc;
//        for (int dir = 0; dir < 4; dir++) {
//            mr = row + delR[dir];
//            mc = col + delC[dir];
//
//            if (mr < 0) mr = N - 1;
//            if (N <= mr) mr = 0;
//            if (mc < 0) mc = M - 1;
//            if (M <= mc) mc = 0;
//            if (visited[mr][mc] || arr[mr][mc] == 1) continue;
//
//            dfs(mr, mc);
//        }
//    }

    static void bfs(int row, int col) {
        q.offer(new int[]{row, col});
        visited[row][col] = true;

        int cr, cc, mr, mc;
        while (!q.isEmpty()) {
            cr = q.peek()[0];
            cc = q.poll()[1];

            for (int dir = 0; dir < 4; dir++) {
                mr = cr + delR[dir];
                mc = cc + delC[dir];

                if (mr < 0) mr = N - 1;
                if (N <= mr) mr = 0;
                if (mc < 0) mc = M - 1;
                if (M <= mc) mc = 0;
                if (visited[mr][mc] || arr[mr][mc] == 1) continue;

                visited[mr][mc] = true;
                q.offer(new int[] {mr, mc});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] || arr[i][j] != 0) continue;

                bfs(i, j);
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}

