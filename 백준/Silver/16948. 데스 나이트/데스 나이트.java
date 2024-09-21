import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] delR = {-2, -2, 0, 0, 2, 2};
    static int[] deLC = {-1, 1, -2, 2, -1, 1};
    static int r1, c1, r2, c2;

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        q.offer(new int[]{r1, c1});
        int cr, cc, mr, mc, size, dist = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.poll()[1];

                if (cr == r2 && cc == c2) {
                    return dist;
                }

                for (int dir = 0; dir < 6; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + deLC[dir];

                    if (mr < 0 || mc < 0 || N <= mr || N <= mc) continue;
                    if (visited[mr][mc]) continue;

                    q.offer(new int[]{mr, mc});
                    visited[mr][mc] = true;
                }
            }

            dist++;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        int ret = bfs();
        System.out.println(ret);
    }
}

