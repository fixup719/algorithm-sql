import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, K});
        visited[0][0][K] = true;

        int cr, cc, ck, mr, mc, size, dist = 1;
        while (!q.isEmpty()) {

            size = q.size();

            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.peek()[1];
                ck = q.poll()[2];

                if (cr == N - 1 && cc == M - 1) return dist;

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || N <= mr || M <= mc) continue;

                    if (map[mr][mc] > 0) {
                        if (ck - 1 < 0) continue;
                        if (visited[mr][mc][ck - 1]) continue;

                        visited[mr][mc][ck - 1] = true;
                        q.offer(new int[]{mr, mc, ck - 1});
                    } else {
                        if (visited[mr][mc][ck]) continue;

                        visited[mr][mc][ck] = true;
                        q.offer(new int[]{mr, mc, ck});
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
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        visited = new boolean[N][M][K + 10];

        System.out.println(bfs());

        br.close();
    }
}