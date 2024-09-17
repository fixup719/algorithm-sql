import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr = new int[6][6];
    static int sr, sc;
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] visited = new boolean[6][6];
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static int bfs() {
        q.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        int cr, cc, mr, mc, size, cnt = 0;
        while (!q.isEmpty()) {
            size = q.size();
            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.poll()[1];

                if (arr[cr][cc] == 1) {
                    return cnt;
                }

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || 5 <= mr || 5 <= mc
                            || arr[mr][mc] == -1 || visited[mr][mc]) continue;

                    q.offer(new int[]{mr, mc});
                    visited[mr][mc] = true;
                }
            }
            cnt++;
        }

        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());

        int ret = bfs();
        System.out.println(ret);
    }
}

