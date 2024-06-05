import java.io.*;
import java.util.*;

// 48분
public class Main {
    static int N, M;
    static char[][] arr;
    static boolean[][][][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int redRow, redCol, blueRow, blueCol;

    /*
       [접근]
       ** 게임이 끝나는 경우
          - 구슬이 더이상 움직이지 않을 때
          - 구멍에 구슬이 빠져 나갈 때 -> 파란 구슬이 먼저 꺼내지면 X


       총 4방향으로 굴릴 수 있음
       어떤 방향으로 굴렀느냐에 따라 경우가 여러 케이스 생김
       최소 횟수를 구하므로 bfs 알고리즘 사용 -> 큐에 빨간 구슬 좌표, 파란 구슬 좌표 저장
       방문 체크 -> 4차원 배열 사용(빨간 구슬 좌표, 파란 구슬 좌표)
     */

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{redRow, redCol, blueRow, blueCol});
        visited[redRow][redCol][blueRow][blueCol] = true;

        int size, count = 0;
        int crRow, crCol, cbRow, cbCol;
        int mrRow, mrCol, mbRow, mbCol;
        boolean redNotFirst, blueNotFirst, isAble;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                crRow = q.peek()[0];
                crCol = q.peek()[1];
                cbRow = q.peek()[2];
                cbCol = q.poll()[3];

                if (arr[crRow][crCol] == 'O') return count;

                for (int dir = 0; dir < 4; dir++) {
                    mrRow = crRow;
                    mrCol = crCol;
                    mbRow = cbRow;
                    mbCol = cbCol;
                    redNotFirst = false;
                    blueNotFirst = false;
                    isAble = true;

                    // 빨간 구슬 구르기
                    while (true) {
                        mrRow += delR[dir];
                        mrCol += delC[dir];

                        // 만약 진행 방향쪽에 파란 구슬이 있다면
                        if (mrRow == cbRow && mrCol == cbCol) redNotFirst = true;

                        if (arr[mrRow][mrCol] == 'O') {
                            // 파란 구슬 먼저 구멍을 만난다면
                            if (redNotFirst) {
                                isAble = false;
                            }
                            break;
                        }

                        // 벽이나 장애물을 만난다면
                        if (arr[mrRow][mrCol] == '#') {
                            if (!redNotFirst) {
                                // 빨간 구슬이 먼저 부딪힌 경우
                                mrRow -= delR[dir];
                                mrCol -= delC[dir];
                            } else {
                                // 파란 구슬이 먼저 부딪힌 경우
                                mrRow -= (delR[dir] * 2);
                                mrCol -= (delC[dir] * 2);
                            }
                            break;
                        }
                    }

                    // 파란 구슬 구르기
                    while (true) {
                        mbRow += delR[dir];
                        mbCol += delC[dir];

                        // 만약 진행 방향쪽에 빨간 구슬이 있다면
                        if (mbRow == crRow && mbCol == crCol) blueNotFirst = true;

                        if (arr[mbRow][mbCol] == 'O') {
                            // 파란 구슬도구멍을 만난다면
                            isAble = false;
                            break;
                        }

                        // 벽이나 장애물을 만난다면
                        if (arr[mbRow][mbCol] == '#') {
                            if (!blueNotFirst) {
                                // 파란 구슬이 먼저 부딪힌 경우
                                mbRow -= delR[dir];
                                mbCol -= delC[dir];
                            } else {
                                // 빨간 구슬이 먼저 부딪힌 경우
                                mbRow -= (delR[dir] * 2);
                                mbCol -= (delC[dir] * 2);
                            }
                            break;
                        }
                    }

                    // 파란구슬이 먼저 또는 동시에 구멍에 빠진 경우
                    if (!isAble) continue;

                    if (visited[mrRow][mrCol][mbRow][mbCol]) continue;

                    visited[mrRow][mrCol][mbRow][mbCol] = true;
                    q.add(new int[]{mrRow, mrCol, mbRow, mbCol});
                }
            }

            if (++count > 10) break;
        }


        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = input.charAt(j);

                if (arr[i][j] == 'R') {
                    redRow = i;
                    redCol = j;
                } else if (arr[i][j] == 'B') {
                    blueRow = i;
                    blueCol = j;
                }
            }
        }

        visited = new boolean[N][M][N][M];

        System.out.println(bfs());

        br.close();
    }
}