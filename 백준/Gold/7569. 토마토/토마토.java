import java.io.*;
import java.util.*;

public class Main {
    static int M, N, H;
    static int[][][] box;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int[] delH = {-1, 1};
    static boolean[][][] visited;
    static Queue<Node> q = new LinkedList<>();
    static int tomatoCnt;
    static int answer;

    static class Node {
        int height;
        int row;
        int col;

        Node(int height, int row, int col) {
            this.height = height;
            this.row = row;
            this.col = col;
        }
    }

    static void bfs() {
        int size, ch, cr, cc, mh, mr, mc;
        while (!q.isEmpty()) {
            size = q.size();

            if (tomatoCnt == 0) return;

            for (int i = 0; i < size; i++) {
                ch = q.peek().height;
                cr = q.peek().row;
                cc = q.poll().col;

                // 상하좌우
                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || N <= mr || M <= mc) continue;

                    if (visited[ch][mr][mc] || box[ch][mr][mc] != 0) continue;

                    q.offer(new Node(ch, mr, mc));
                    visited[ch][mr][mc] = true;
                    box[ch][mr][mc] = 1;
                    tomatoCnt--;
                }

                // 앞뒤
                for (int dir = 0; dir < 2; dir++) {
                    mh = ch + delH[dir];

                    if (mh < 0 || H <= mh) continue;

                    if (visited[mh][cr][cc] || box[mh][cr][cc] != 0) continue;

                    q.offer(new Node(mh, cr, cc));
                    visited[mh][cr][cc] = true;
                    box[mh][cr][cc] = 1;
                    tomatoCnt--;
                }
            }
            answer++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];
        visited = new boolean[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());

                    if (box[i][j][k] == 1){
                        q.offer(new Node(i, j, k));
                        visited[i][j][k] = true;
                    } else if (box[i][j][k] == 0) {
                        // 안익은 토마토 개수 세기
                        tomatoCnt++;
                    }
                }
            }
        }

        if (tomatoCnt == 0) {
            bw.write("0");
        } else {
            bfs();
            if (tomatoCnt > 0) bw.write("-1");
            else bw.write(String.valueOf(answer));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}