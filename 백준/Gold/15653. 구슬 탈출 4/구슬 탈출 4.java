import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;
    static int reR,reC, blR, blC;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{reR, reC, blR, blC});
        visited[reR][reC][blR][blC] = true;

        int size, cRedRow, cRedCol, cBlueRow, cBlueCol, mRedRow, mRedCol, mBlueRow, mBlueCol, flag, dist = 0;
        boolean blueStop, redStop;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cRedRow = q.peek()[0];
                cRedCol = q.peek()[1];
                cBlueRow = q.peek()[2];
                cBlueCol = q.poll()[3];

                for (int dir = 0; dir < 4; dir++) {
                    mRedRow = cRedRow;
                    mRedCol = cRedCol;
                    mBlueRow = cBlueRow;
                    mBlueCol = cBlueCol;
                    redStop = false;
                    blueStop = false;
                    flag = 0;

                    // 기울이니까,, 장애물이 없을 때까지 굴럴야한다...ㅠㅠ
                    while (true) {
                        if (!redStop) {
                            mRedRow += delR[dir];
                            mRedCol += delC[dir];

                        }

                        if (!blueStop) {
                            mBlueRow += delR[dir];
                            mBlueCol += delC[dir];
                        }

                        if (redStop && blueStop) break;

                        // 둘이 같은 칸에도 안 됨
                        if (mRedRow == mBlueRow && mRedCol == mBlueCol) {
                            redStop = true;
                            blueStop = true;
                        }

                        // 파란공 또는 빨간공은 벽이나 장애물을 만나면 이동 X
                        if (board[mBlueRow][mBlueCol] == '#') {
                            mBlueRow -= delR[dir];
                            mBlueCol -= delC[dir];
                            blueStop = true;
                            if (flag == 0) flag = 1;
                        }
                        if (board[mRedRow][mRedCol] == '#') {
                            mRedRow -= delR[dir];
                            mRedCol -= delC[dir];
                            redStop = true;
                            if (flag == 0) flag = 2;
                        }

                        // 구멍을 만나면 일단 멈추기
                        if (board[mBlueRow][mBlueCol] == 'O') blueStop = true;
                        if (board[mRedRow][mRedCol] == 'O') redStop = true;
                    }

                    // 파란 공이 먼저 구멍에 들어가면 X (또는 동시에)
                    if (board[mBlueRow][mBlueCol] == 'O') continue;
                    // 빨간 공이  먼저 구멍에 들어가면 끝
                    if (board[mRedRow][mRedCol] == 'O') return dist + 1;

                    if (mRedRow == mBlueRow && mRedCol == mBlueCol){
                        if (flag == 1) {
                            mRedRow -= delR[dir];
                            mRedCol -= delC[dir];
                        } else if (flag == 2) {
                            mBlueRow -= delR[dir];
                            mBlueCol -= delC[dir];
                        }
                    }


                    if (visited[mRedRow][mRedCol][mBlueRow][mBlueCol]) continue;

                    visited[mRedRow][mRedCol][mBlueRow][mBlueCol] = true;
                    q.offer(new int[]{mRedRow, mRedCol, mBlueRow, mBlueCol});
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

        board = new char[N][M];

        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);

                if (board[i][j] == 'R') {
                    reR = i;
                    reC = j;
                } else if (board[i][j] == 'B') {
                    blR = i;
                    blC = j;
                }
            }
        }

        visited = new boolean[N][M][N][M];

        System.out.println(bfs());

        br.close();
    }
}