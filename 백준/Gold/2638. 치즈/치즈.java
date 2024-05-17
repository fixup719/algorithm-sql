import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static Queue<int[]> q = new LinkedList<>();
    static final int EXTERIOR = 0;
    static final int CHEEZE = 1;
    static final int INTERIOR = 2;
    static final int willMelt = 3;
    static int totalCheeze = 0;
    static int answer = 0;

    static void makeMap(int row, int col, int state) {
        map[row][col] = state;

        int mrow, mcol;
        for (int dir = 0; dir < 4; dir++) {
            mrow = row + delR[dir];
            mcol = col + delC[dir];

            if (mrow < 0 || mcol < 0 || N <= mrow || M <= mcol
                    || visited[mrow][mcol] || map[row][col] != map[mrow][mcol]) continue;

            visited[mrow][mcol] = true;
            makeMap(mrow, mcol, state);
        }
    }

    static void insertOxygen(int row, int col) {
        map[row][col] = EXTERIOR;

        int mrow, mcol;
        for (int dir = 0; dir < 4; dir++) {
            mrow = row + delR[dir];
            mcol = col + delC[dir];

            if (mrow < 0 || mcol < 0 || N <= mrow || M <= mcol
                    || visited[mrow][mcol] || map[mrow][mcol] == CHEEZE) continue;

            visited[mrow][mcol] = true;
            insertOxygen(mrow, mcol);
        }
    }

    static void setWillMelt(int row, int col) {
        q.offer(new int[]{row, col});
        visited[row][col] = true;

        int crow, ccol, mrow, mcol, cnt;
        while (!q.isEmpty()) {
            crow = q.peek()[0];
            ccol = q.poll()[1];
            cnt = 0;

            for (int dir = 0; dir < 4; dir++) {
                mrow = crow + delR[dir];
                mcol = ccol + delC[dir];

                if (mrow < 0 || mcol < 0 || N <= mrow || M <= mcol
                        || visited[mrow][mcol]) continue;

                // 치즈면 담기
                if (map[mrow][mcol] == CHEEZE){
                    q.offer(new int[]{mrow, mcol});
                    visited[mrow][mcol] = true;
                } else if (map[mrow][mcol] == EXTERIOR) {
                    // 외부 공기면 카운트
                    cnt++;
                }
            }

            if (cnt >= 2) {
                // 녹을 예정
                map[crow][ccol] = willMelt;
            }

        }
    }

    // 치즈 녹이기
    static void meltCheeze() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == willMelt){
                    map[i][j] = EXTERIOR;
                    totalCheeze--;
                }
            }
        }
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == CHEEZE) totalCheeze++;
            }
        }

        // 맵구성하기 : 외부공기 = 0, 치즈 = 1, 내부 공기 = 2
        visited = new boolean[N][M];
        boolean meetOxygen = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) continue;

                if (map[i][j] == 0) {
                    if (!meetOxygen){
                        makeMap(i, j, EXTERIOR);
                        meetOxygen = true;
                    } else makeMap(i, j, INTERIOR);
                } else if (map[i][j] == CHEEZE) {
                    makeMap(i, j, CHEEZE);
                }
            }
        }

        while (totalCheeze > 0) {
            for (boolean[] v : visited) {
                Arrays.fill(v, false);
            }

            // 시뮬레이션 - 녹을 치즈 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == CHEEZE) {
                        setWillMelt(i, j);
                    }
                }
            }

            // 치즈 녹이기
            meltCheeze();

            for (boolean[] v : visited) {
                Arrays.fill(v, false);
            }

            // 외부 공기 유입하기
            insertOxygen(0,0);

            answer++;
        }

        System.out.println(answer);

    }
}