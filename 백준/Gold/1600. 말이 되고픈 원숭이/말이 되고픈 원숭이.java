import java.io.*;
import java.util.*;

public class Main {
    static int K; // 원숭이가 말처럼 움직일 수 있는 최소 횟수
    static int W, H; // 가로길이(col) W, 세로길이(row) H
    static int[][] map; // 격자판
    static boolean[][][] visited; // 방문 체크

    // 인접한 칸으로 움직이기(상,하,좌,우)
    static int[] monkDelR = {-1, 1, 0, 0};
    static int[] monkDelC = {0, 0, -1, 1};

    // 원숭이처럼 움직이기
    static int[] horseDelR = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] horseDelC = {-2, -1, 1, 2, -2, -1, 1, 2};

    static boolean check(int row, int col, int horseMoveCnt) {
        return 0 <= row && 0 <= col && row < H && col < W && map[row][col] == 0 && !visited[horseMoveCnt][row][col];
    }

    static class Node {
        int row;
        int col;
        int cnt;

        Node(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }

    static int bfs(int row, int col) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col, 0));
        visited[row][col][0] = true;

        int crow, ccol, mrow, mcol, horseMoveCnt, size, totalCnt = 0;
        while (!q.isEmpty()) {

            size = q.size();

            for (int s = 0; s < size; s++) {
                crow = q.peek().row;
                ccol = q.peek().col;
                horseMoveCnt = q.poll().cnt;

                if (crow == H - 1 && ccol == W - 1) {
                    return totalCnt;
                }

                // 말처럼 움직이기
                if (horseMoveCnt < K) {
                    for (int dir = 0; dir < 8; dir++) {
                        mrow = crow + horseDelR[dir];
                        mcol = ccol + horseDelC[dir];

                        if (!check(mrow, mcol, horseMoveCnt + 1)) continue;

                        visited[horseMoveCnt + 1][mrow][mcol] = true;
                        q.offer(new Node(mrow, mcol, horseMoveCnt + 1));
                    }
                }

                // 인접한 곳 움직이기
                for (int dir = 0; dir < 4; dir++) {
                    mrow = crow + monkDelR[dir];
                    mcol = ccol + monkDelC[dir];

                    if (!check(mrow, mcol, horseMoveCnt)) continue;

                    visited[horseMoveCnt][mrow][mcol] = true;
                    q.offer(new Node(mrow, mcol, horseMoveCnt));
                }
            }

            totalCnt++;
        }

        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[31][H][W];

        System.out.println(bfs(0, 0));

        br.close();
    }
}