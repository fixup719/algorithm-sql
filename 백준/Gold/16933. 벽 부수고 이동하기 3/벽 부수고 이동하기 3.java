import java.io.*;
import java.util.*;


public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static class Node{
        int row;
        int col;
        int crush;
        int isNight;
        int moveCnt;

        Node(int row, int col, int crush, int isNight, int moveCnt) {
            this.row = row;
            this.col = col;
            this.crush = crush;
            this.isNight = isNight;
            this.moveCnt = moveCnt;
        }
    }
    static int bfs(int row, int col) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(row, col, 0, 0, 1));
        visited[row][col][0] = true;

        int cr, cc, mr, mc, crush, isNight, moveCnt, size;
        while (!q.isEmpty()) {

            size = q.size();

            for (int s = 0; s < size; s++) {
                cr = q.peek().row;
                cc = q.peek().col;
                crush = q.peek().crush;
                isNight = q.peek().isNight;
                moveCnt = q.poll().moveCnt;

                if (cr == N - 1 && cc == M - 1) {
                    return moveCnt;
                }

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || N <= mr || M <= mc) continue;

                    if (map[mr][mc] == 1) {
                        // 벽이라면
                        if (isNight == 0 && crush < K && !visited[mr][mc][crush + 1]) {
                            // 낮이고 부순 벽이 K보다 작다면 부숨
                            visited[mr][mc][crush + 1] = true;
                            q.offer(new Node(mr, mc, crush + 1, 1, moveCnt + 1));
                        } else if (isNight == 1 && crush < K) {
                            // 밤이고 부순 벽이 K보다 작다면 부수진 않고 제자리에 기다리기
                            visited[cr][cc][crush] = true;
                            q.offer(new Node(cr, cc, crush, 0, moveCnt + 1));
                        }
                    } else {
                        // 빈공간이라면 그냥 이동
                        if (!visited[mr][mc][crush]) {
                            visited[mr][mc][crush] = true;
                            q.offer(new Node(mr, mc, crush, (isNight + 1) % 2, moveCnt + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][K + 1];

        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(bfs(0, 0));
    }
}