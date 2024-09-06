import java.io.*;
import java.util.*;

public class Main {
    static int N, M, hx, hy, ex, ey;
    static int[][] arr;
    static boolean[][][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{hx, hy, 0});
        visited[hx][hy][0] = true;

        int cr, cc, use, mr, mc, size, dist = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.peek()[1];
                use = q.poll()[2];

                if (cr == ex && cc == ey) {
                    return dist;
                }

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || N <= mr || M <= mc || visited[mr][mc][use]) continue;

                    // 벽을 만났다면
                    if (arr[mr][mc] > 0) {
                        // 이미 써버려서 마법 못 쓸 경우 pass
                        if (use > 0) continue;
                        // 마법쓰기
                        visited[mr][mc][arr[mr][mc]] = true;
                        q.offer(new int[]{mr, mc, arr[mr][mc]});
                    } else {
                    //벽이 아니라면
                        visited[mr][mc][use] = true;
                        q.offer(new int[]{mr, mc, use});
                    }
                }
            }
            dist++;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        hx = Integer.parseInt(st.nextToken()) - 1;
        hy = Integer.parseInt(st.nextToken()) - 1;
        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M][2];

        int ret = bfs();
        System.out.println(ret);

    }
}

