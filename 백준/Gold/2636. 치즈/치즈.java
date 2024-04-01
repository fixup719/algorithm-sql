import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] arr;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static final int CHEEZE = 1;
    static final int MELT = -1;
    static final int AIR = 2;
    static final int HOLE = 0;
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] visited;

    static boolean binaryCheck(int row, int col) {
        return 0 <= row && 0 <= col && row < R && col < C;
    }

    static int cheezeCnt() {
        int answer = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] == 1) answer++;
            }
        }

        return answer;
    }

    static void bfs(int row, int col) {
        q.offer(new int[]{row, col});
        visited[row][col] = true;

        int cr, cc, mr, mc;
        while (!q.isEmpty()) {
            cr = q.peek()[0];
            cc = q.poll()[1];

            for (int dir = 0; dir < 4; dir++) {
                mr = cr + delR[dir];
                mc = cc + delC[dir];

                if (!binaryCheck(mr, mc) || visited[mr][mc] || arr[mr][mc] == CHEEZE) continue;

                arr[mr][mc] = AIR;
                q.offer(new int[]{mr, mc});
                visited[mr][mc] = true;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[R][C];

        int time = 0;
        ArrayList<Integer> pieces = new ArrayList<>();
        while (true) {

            pieces.add(cheezeCnt());

            // 치즈가 다 녹았다면 종료
            if (pieces.get(time) == 0) break;

            // 구멍에 공기 유입 되었는지 체크
            bfs(0, 0);
            for (int i = 0; i < R; i++) {
                Arrays.fill(visited[i], false);
            }

            // 공기 접촉 면 체크
            int mrow, mcol;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (arr[i][j] == 1) {
                        for (int d = 0; d < 4; d++) {
                            mrow = i + delR[d];
                            mcol = j + delC[d];

                            if (!binaryCheck(mrow, mcol)) continue;

                            if (arr[mrow][mcol] == AIR) {
                                arr[i][j] = MELT;
                                break;
                            }
                        }
                    }
                }
            }

            // 공기와 접촉한 치즈는 공기로 바꿔주기
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (arr[i][j] == MELT) {
                        arr[i][j] = AIR;
                    }
                }
            }

            // 시간 늘리기
            time++;
        }

        bw.write(time + "\n" + pieces.get(time - 1));
        bw.flush();
        bw.close();
        br.close();
    }
}