import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M; // map 크기
    static int S; // CCTV 개수
    static int zero; // 0개수
    static int[][] map; // 사무실 정보
    static int[][] copyMap; // 사무실 복사본
    static ArrayList<CCTV> selected; // CCTV별로 방향을 선택한 경우
    static int[] delR = {-1, 1, 0 ,0}; // 상하좌우
    static int[] delC = {0, 0, -1, 1}; // 상하좌우
    static int[][][] cctvs = {{},
            {{0}, {1}, {2}, {3}},
            {{0, 1}, {2, 3}},
            {{0, 3}, {0, 2}, {1, 3}, {1, 2}},
            {{0, 2, 3}, {1, 2, 3}, {0, 1, 2}, {0, 1, 3}},
            {{0, 1, 2, 3}}};
    static int answer = Integer.MAX_VALUE; // 사각지대 최솟값

    static class CCTV {
        int no; // CCTV 번호
        int dir; // CCTV 방향
        int row; // cctv 좌표 row값
        int col; // cctv 좌표 col값

        CCTV(int no, int dir, int row, int col) {
            this.no = no;
            this.dir = dir;
            this.row = row;
            this.col = col;
        }
    }

    static void copyMap() {
        zero = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
                if(copyMap[i][j] == 0) zero++;
            }
        }
    }

    static void updateMap(int no, int dir, int row, int col) {
        for (int k = 0; k < cctvs[no][dir].length; k++) {
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{row, col});

            int crow, ccol, mrow, mcol;
            while (!q.isEmpty()) {
                crow = q.peek()[0];
                ccol = q.poll()[1];

                mrow = crow + delR[cctvs[no][dir][k]];
                mcol = ccol + delC[cctvs[no][dir][k]];

                if (mrow < 0 || mcol < 0 || N <= mrow || M <= mcol || copyMap[mrow][mcol] == 6) break;

                if (copyMap[mrow][mcol] == 0) {
                    zero--;
                    copyMap[mrow][mcol] = -1;
                    q.offer(new int[]{mrow, mcol});
                } else {
                    q.offer(new int[]{mrow, mcol});
                }
            }
        }
    }

    static  void recur(int cur) {
        if (cur == S) {
            copyMap();
            for (int i = 0; i < S; i++) {
                updateMap(selected.get(i).no, selected.get(i).dir, selected.get(i).row, selected.get(i).col);
            }
            answer = Math.min(zero, answer);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (i >= 2 && selected.get(cur).no == 2) {
                selected.get(cur).dir = i % 2;
            } else if (i >= 1 && selected.get(cur).no == 5) {
                selected.get(cur).dir = i % 1;
            } else {
                selected.get(cur).dir = i;
            }
            recur(cur + 1);
        }
    }

    static void printMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(copyMap[i][j]+" ");
            }
            System.out.println();
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
        copyMap = new int[N][M];
        selected = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 1) selected.add(new CCTV(1, -1, i, j));
                else if(map[i][j] == 2) selected.add(new CCTV(2, -1, i, j));
                else if(map[i][j] == 3) selected.add(new CCTV(3, -1, i, j));
                else if(map[i][j] == 4) selected.add(new CCTV(4, -1, i, j));
                else if(map[i][j] == 5) selected.add(new CCTV(5, -1, i, j));
            }
        }
        S = selected.size();
        recur(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}