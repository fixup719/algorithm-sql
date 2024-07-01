import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static class Node implements Comparable<Node> {
        int row;
        int col;
        int cnt;

        Node(int row, int col, int cnt) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node node) {
            return this.cnt - node.cnt;
        }
    }

    static int bfs() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(0, 0, 0));
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;

        int cr, cc, cn, mr, mc;
        while (!q.isEmpty()) {
            cr = q.peek().row;
            cc = q.peek().col;
            cn = q.poll().cnt;

            if (cr == N - 1 & cc == N - 1) return cn;

            for (int dir = 0; dir < 4; dir++) {
                mr = cr + delR[dir];
                mc = cc + delC[dir];

                if (mr < 0 || mc < 0 || N <= mr || N <= mc || visited[mr][mc]) continue;

                visited[mr][mc] = true;
                if (map[mr][mc] == 0) {
                    q.offer(new Node(mr, mc, cn + 1));
                } else {
                    q.offer(new Node(mr, mc, cn));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }
}
