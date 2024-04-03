import java.io.*;
import java.util.*;


// 메모리 초과 이슈
public class Main {
    static int M, N;
    static int[][] arr;
    static int sr, sc, sd, er,ec, ed;
    static boolean[][][] visited;
    // 동북서남 (동1->0 / 서2->2 / 북4->1 / 남3->3)
    static int[] delR = {0, -1, 0, 1};
    static int[] delC = {1, 0, -1, 0};


    static class Node implements Comparable<Node> {
        int row;
        int col;
        int dir;
        int cmd;

        Node(int row, int col, int dir, int cmd) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.cmd = cmd;
        }

        @Override
        public int compareTo(Node node) {
            return this.cmd - node.cmd;
        }

    }

    static boolean boundaryCheck(int row, int col) {
        return 0 <= row && 0 <= col && row < M && col < N;
    }

    static int bfs(int row, int col, int dir) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(row, col, dir, 0));
        visited[dir][row][col] = true;

        int cr, cc, cd, cm, nr, nc, nd, size;
        while (!pq.isEmpty()) {
            size = pq.size();

            for (int s = 0; s < size; s++) {
                cr = pq.peek().row;
                cc = pq.peek().col;
                cd = pq.peek().dir;
                cm = pq.poll().cmd;

                if (cr == er && cc == ec && cd == ed) return cm;

                // 명령 1 : Go K (1~3) 해당 방향으로 K만큼 이동
                // 명령 2 : Turn dir (left or right) 각각 왼쪽 또는 오른쪽으로 90도 회전
                /* 나올 수 있는 경우
                   현재 방향으로 +1,+2,+3 이동
                   왼쪽 90도 회전 후 +1,+2,+3 이동 (dir+1)%4
                        동0 -> 북1 / 북1 -> 서2 / 서2 -> 남3 / 남3 -> 동0
                   오른쪽 90도 회전 후 +1,+2,+3 이동 (dir+3)%4
                        동0 -> 남3 / 남3 -> 서2 / 서2 -> 북1 / 북1 -> 동0
                */

                // 회전 안하고 K이동 -> 여기서 주의할 것 -> k만큼 이동하는 과정에서 1이 있으면 안 됨
                for (int i = 1; i <= 3 ; i++) {
                    nr = cr + delR[cd] * i;
                    nc = cc + delC[cd] * i;

                    if (!boundaryCheck(nr,nc) || visited[cd][nr][nc]) continue;

                    if (arr[nr][nc] == 1) break;

                    pq.offer(new Node(nr, nc, cd, cm + 1));
                    visited[cd][nr][nc] = true;
                }

                // 왼쪽 회전
                nd = (cd + 1) % 4;
                if (!visited[nd][cr][cc]) {
                    pq.offer(new Node(cr, cc, nd, cm + 1));
                    visited[nd][cr][cc] = true;
                }


                // 오른쪽 회전
                nd = (cd + 3) % 4;
                if (!visited[nd][cr][cc]) {
                    pq.offer(new Node(cr, cc, nd, cm + 1));
                    visited[nd][cr][cc] = true;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        sd = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;
        ed = Integer.parseInt(st.nextToken());


        // 방향 수정한 방식으로 바꿔주기
        if (sd == 1) sd = 0;
        else if (sd == 4) sd = 1;
        if (ed == 1) ed = 0;
        else if (ed == 4) ed = 1;

        visited = new boolean[4][M][N];

        bw.write(String.valueOf(bfs(sr, sc, sd)));
        bw.flush();
        bw.close();
        br.close();
    }
}