import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int[][] cnt;
    static int[][] pos;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static int[] bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[3][N][M];
        for (int i = 0; i < 3; i++) {
            q.offer(new int[]{i, pos[i][0], pos[i][1]});
            visited[i][pos[i][0]][pos[i][1]] = true;
        }

        int no, cr, cc, mr, mc, size, time = 0, total = 0;
        boolean flag = false;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                no = q.peek()[0];
                cr = q.peek()[1];
                cc = q.poll()[2];

                cnt[cr][cc]++;

                if (cnt[cr][cc] == 3) {
                    flag = true;
                    total++;
                }

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || N <= mr || M <= mc) continue;
                    if (visited[no][mr][mc] || arr[mr][mc] == 1) continue;

                    visited[no][mr][mc] = true;
                    q.offer(new int[]{no, mr, mc});
                }
            }
            if (flag) return new int[] {time, total};
            time++;
        }

        return new int[]{-1};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        cnt = new int[N][M];
        pos = new int[3][2];

        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = input.charAt(j) - '0';
            }
        }

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken()) - 1;
            pos[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }

        int[] ans = bfs();

        if (ans[0] == -1) {
            System.out.println(-1);
        } else {
            System.out.println(ans[0]);
            System.out.println(ans[1]);
        }
    }
}