import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static int bfs(int row, int col, int wall) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col, wall});
        visited[wall][row][col] = true;

        int cr, cc, cw, mr, mc, size, dist = 1;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.peek()[1];
                cw = q.poll()[2];

                if (cr == N - 1 && cc == M - 1) return dist;

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || N <= mr || M <= mc) continue;

                    // 이미 벽을 만난 경우 또 벽을 만났을 경우 pass
                    if (arr[mr][mc] == 1 && cw == 1) continue;

                    // 방문체크 
                    if (visited[cw + arr[mr][mc]][mr][mc]) continue;

                    q.offer(new int[]{mr, mc, cw + arr[mr][mc]});
                    visited[cw + arr[mr][mc]][mr][mc] = true;
                }
            }

            dist++;
        }

        return -1;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = input.charAt(j) - '0';
            }
        }

        visited = new boolean[2][N][M];
        bw.write(String.valueOf(bfs(0, 0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }
}