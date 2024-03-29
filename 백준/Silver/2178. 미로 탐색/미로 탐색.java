import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int dist;

    static void bfs(int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{row, col});
        visited[row][col] = true;

        int cRow, cCol, mRow, mCol, size;
        while (!q.isEmpty()) {

            size = q.size();

            for (int i = 0; i < size; i++) {
                cRow = q.peek()[0];
                cCol = q.poll()[1];

                if (cRow == N - 1 && cCol == M - 1) return;

                for (int dir = 0; dir < 4; dir++) {
                    mRow = cRow + delR[dir];
                    mCol = cCol + delC[dir];

                    if (mRow < 0 || mCol < 0 || N <= mRow || M <= mCol
                            || visited[mRow][mCol] || map[mRow][mCol] == 0) continue;

                    q.offer(new int[]{mRow, mCol});
                    visited[mRow][mCol] = true;
                }
            }

            dist++;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        visited = new boolean[N][M];

        bfs(0, 0);

        bw.write(String.valueOf(dist + 1));
        bw.flush();
        bw.close();
        br.close();
    }
}