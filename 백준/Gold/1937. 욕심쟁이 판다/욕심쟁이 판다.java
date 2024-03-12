import java.io.*;
import java.util.*;

public class Main {
    static int N; // 숲 크기
    static int[][] map; // 숲 정보
    static int[] delR = {-1, 1, 0, 0}; // 행 이동 : 상하좌우
    static int[] delC = {0, 0, -1, 1}; // 열 이동 : 상하좌우
    static int[][] dp;
    static int answer; // 경로 개수

    // 경계 체크
    static boolean borderCheck(int row, int col) {
        return 0 <= row && 0 <= col && row < N && col < N;
    }

    // 이동할 곳이 있는지
    static boolean check(int row, int col) {

        int mrow, mcol, cnt = 0;
        for (int dir = 0; dir < 4; dir++) {
            mrow = row + delR[dir];
            mcol = col + delC[dir];

            if (!borderCheck(mrow, mcol)) continue;

            if (map[row][col] < map[mrow][mcol]) cnt++;
        }

        if (cnt == 0) return false;
        else return true;
    }

    // 1.백트랙킹으로 생각하기
//    static void recur(int row, int col, int total) {
//
//        // 종료 조건은 더이상 상하좌우로 이동할 수 없을 때
//        if (!check(row, col)) {
//            answer = Math.max(answer, total);
//            return;
//        }
//
//        int mrow, mcol;
//        for (int dir = 0; dir < 4; dir++) {
//            mrow = row + delR[dir];
//            mcol = col + delC[dir];
//
//            if (!borderCheck(mrow, mcol) || map[row][col] >= map[mrow][mcol]) continue;
//
//            recur(mrow, mcol, total + 1);
//        }
//
//    }

    // 2. 메모이제이션 + 함수적용 : 최대 칸을 방문하는 경우를 반환
    static int recur(int row, int col) {

        // 종료 조건은 더이상 상하좌우로 이동할 수 없을 때
        if (!check(row, col)) return 0;

        if (dp[row][col] != -1) return dp[row][col];

        int mrow, mcol, ret = 0;
        for (int dir = 0; dir < 4; dir++) {
            mrow = row + delR[dir];
            mcol = col + delC[dir];

            if (!borderCheck(mrow, mcol) || map[row][col] >= map[mrow][mcol]) continue;

            ret = Math.max(ret, recur(mrow, mcol) + 1);
        }

        dp[row][col] = ret;
        return dp[row][col];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[510][510];
        for (int i = 0; i < 510; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer = Math.max(answer, recur(i, j) + 1);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}