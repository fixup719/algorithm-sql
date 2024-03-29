import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int answer;

    static void bfs(int row, int col) {
        q.offer(new int[]{row, col});
        visited[row][col] = true;

        int cRow, cCol, mRow, mCol, size, dist = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int i = 0; i < size; i++) {
                cRow = q.peek()[0];
                cCol = q.poll()[1];

                for (int dir = 0; dir < 4; dir++) {
                    mRow = cRow + delR[dir];
                    mCol = cCol + delC[dir];

                    if (mRow < 0 || mCol < 0 || N <= mRow || M <= mCol
                            || visited[mRow][mCol] || map[mRow][mCol] == 'W') continue;

                    q.offer(new int[]{mRow, mCol});
                    visited[mRow][mCol] = true;
                }
            }

            dist++;
        }

        answer = Math.max(answer, dist - 1);

    }

    static void initVisited() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    initVisited();
                    bfs(i, j);
                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}