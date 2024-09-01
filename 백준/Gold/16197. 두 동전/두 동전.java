import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board = new char[30][30];
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static ArrayList<int[]> coins = new ArrayList<>();
    static boolean[][][][] visited = new boolean[30][30][30][30];

    static int move() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{coins.get(0)[0], coins.get(0)[1], coins.get(1)[0], coins.get(1)[1]});
        visited[coins.get(0)[0]][coins.get(0)[1]][coins.get(1)[0]][coins.get(1)[1]] = true;

        int cr1, cc1, mr1, mc1, cr2, cc2, mr2, mc2, remain, size, total = 1;
        while (!q.isEmpty()) {

            size = q.size();
            for (int s = 0; s < size; s++) {
                cr1 = q.peek()[0];
                cc1 = q.peek()[1];
                cr2 = q.peek()[2];
                cc2 = q.poll()[3];

                for (int dir = 0; dir < 4; dir++) {
                    // 두 동전 동시에 이동
                    remain = 2;
                    mr1 = cr1 + delR[dir];
                    mc1 = cc1 + delC[dir];
                    mr2 = cr2 + delR[dir];
                    mc2 = cc2 + delC[dir];

                    // 경계밖을 떠났으므로 해당 동전은 탈락
                    if (mr1 < 0 || mc1 < 0 || N <= mr1 || M <= mc1) remain--;
                    if (mr2 < 0 || mc2 < 0 || N <= mr2 || M <= mc2) remain--;

                    // 동전 모두 탈락
                    if (remain == 0) continue;
                    // 동전 하나만 살아남음
                    if (remain == 1) return total;
                    // 동전 둘다 살아남음
                    if (remain == 2) {
                        // 동전1이 벽을 만났다면 현재위치로 고정
                        if (board[mr1][mc1] == '#') {
                            mr1 = cr1;
                            mc1 = cc1;
                        }
                        // 동전2가 벽을 만났다면 현재위치로 고정
                        if (board[mr2][mc2] == '#') {
                            mr2 = cr2;
                            mc2 = cc2;
                        }
                        // 이미 방문한 케이스라면 pass
                        if (visited[mr1][mc1][mr2][mc2]) continue;
                        // 현재 두 동전의 위치를 큐에 담고 방문처리
                        q.offer(new int[]{mr1, mc1, mr2, mc2});
                        visited[mr1][mc1][mr2][mc2] = true;
                    }
                }
            }

            // 버튼 클릭
            total++;
            // 10번 초과한 경우 무조건 -1 반환
            if (total > 10) return -1;
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j);
                if (board[i][j] == 'o') coins.add(new int[]{i, j});
            }
        }

        int ret = move();

        System.out.println(ret);
    }
}

