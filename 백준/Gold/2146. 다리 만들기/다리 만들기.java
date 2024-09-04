import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static boolean[][][] visited2;
    static Queue<int[]> q = new LinkedList<>();
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};


    // 섬마다 번호 붙이기
    static void dfs(int row, int col, int no) {
        visited[row][col] = true;
        arr[row][col] = no;
        q.offer(new int[]{row, col, no});
        
        int mrow, mcol;
        for (int dir = 0; dir < 4; dir++) {
            mrow = row + delR[dir];
            mcol = col + delC[dir];

            if (mrow < 0 || mcol < 0 || N <= mrow || N <= mcol
                    || visited[mrow][mcol] || arr[mrow][mcol] == 0) continue;

            dfs(mrow, mcol, no);
        }
    }

    // 섬끼리 최단 경로 찾기
    static int bfs() {

        int ans = 1 << 30, cr, cc, no, mr, mc, size, dist = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.peek()[1];
                no = q.poll()[2];

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || N <= mr || N <= mc) continue;
                    if (visited2[mr][mc][no]) continue;
                    if (arr[mr][mc] != 0 && arr[mr][mc] != no) return dist;

                    visited2[mr][mc][no] = true;
                    q.offer(new int[]{mr, mc, no});
                }
            }

            dist++;
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬마다 번호 붙이기
        int idx = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || arr[i][j] == 0) continue;

                dfs(i, j, idx++);
            }
        }

        visited2 = new boolean[N][N][idx];
        int ret = bfs();

        System.out.println(ret);
    }
}

