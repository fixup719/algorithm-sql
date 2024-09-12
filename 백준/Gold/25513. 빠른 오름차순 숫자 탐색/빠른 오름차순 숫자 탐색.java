import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr = new int[5][5];
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int sr, sc;

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[5][5][10];
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc][0] = true;

        int cr, cc, mr, mc, prv, size, ans = 0;
        while (!q.isEmpty()) {
            size = q.size();
            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.peek()[1];
                prv = q.poll()[2];

                if (prv == 6) return ans;

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || 5 <= mr || 5 <= mc
                            || arr[mr][mc] == -1 || visited[mr][mc][prv]) continue;

                    if (arr[mr][mc] == prv + 1) {
                        visited[mr][mc][prv + 1] = true;
                        q.offer(new int[]{mr, mc, prv + 1});
                    } else {
                        visited[mr][mc][prv] = true;
                        q.offer(new int[]{mr, mc, prv});
                    }
                }
            }
            ans++;
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

