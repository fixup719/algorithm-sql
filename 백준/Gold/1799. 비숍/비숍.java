import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N; // 체스판 크기
    static int[][] map; // 체스판
    static int[][] copyMap; // 체스판 복사본
    static int blackCnt; // 검정칸만 돌았을때 세워놓은 비숍 개수
    static int whiteCnt; // 비숍 개수


    static void onCopyMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
    }

    static boolean check(int row, int col) {

        // 현재 위치 기준 좌측상단대각선 탐색하기
        int trow = row, tcol = col;
        while (true) {
            trow--;
            tcol--;

            if (trow < 0 || tcol < 0 || N <= trow || N <= tcol) break;

            if (copyMap[trow][tcol] == 2) return false;
        }

        // 현재 위치 기준 우측상단대각선 탐색하기
        trow = row; tcol = col;
        while (true) {
            trow--;
            tcol++;

            if (trow < 0 || tcol < 0 || N <= trow || N <= tcol) break;

            if (copyMap[trow][tcol] == 2) return false;
        }

        return true;
    }

    static void recur(int row, int col, int total, char color) {
        if (col >= N) {
            row++;
            if (color == 'B') {
                // 검정칸일때 -> (짝수, 짝수) 또는 (홀수,홀수) 좌표만 방문하게 되므로
                if (row % 2 == 0) col = 0;
                else col = 1;
            } else {
                // 흰색칸일때
                if (row % 2 == 1) col = 0;
                else col = 1;
            }
        }

        if (row >= N) {
            if (color == 'B') {
                // 검정 칸일때 최대 비숍 개수
                blackCnt = Math.max(blackCnt, total);
            } else {
                // 흰색 칸일때 최대 비숍 개수
                whiteCnt = Math.max(whiteCnt, total);
            }
            return;
        }

        if (copyMap[row][col] == 1) {
            if (check(row, col)) {
                copyMap[row][col]++;

                recur(row, col + 2, total + 1, color);

                copyMap[row][col]--;
            }
        }

        recur(row, col + 2, total, color);

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        onCopyMap();
        recur(0, 0, 0, 'B');

        onCopyMap();
        recur(0, 1, 0, 'W');

        bw.write(String.valueOf(blackCnt + whiteCnt));
        bw.flush();
        bw.close();
        br.close();
    }
}