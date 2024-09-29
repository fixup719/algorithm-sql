import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] dist;
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] visited;
    static int sr, sc;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static void bfs() {
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc] = true;

        int cr, cc, cd, mr, mc;
        while (!q.isEmpty()) {
            cr = q.peek()[0];
            cc = q.peek()[1];
            cd = q.poll()[2];

            for (int dir = 0; dir < 4; dir++) {
                mr = cr + delR[dir];
                mc = cc + delC[dir];

                if (mr < 0 || mc < 0 || N <= mr || M <= mc
                        || visited[mr][mc] || arr[mr][mc] == 0) continue;

                dist[mr][mc] = cd + 1;
                visited[mr][mc] = true;
                q.offer(new int[]{mr, mc, cd + 1});
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
        dist = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if (arr[i][j] == 2) {
                    sr = i;
                    sc = j;
                }
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr[i][j] == 1 && dist[i][j] == 0) sb.append("-1 ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}

