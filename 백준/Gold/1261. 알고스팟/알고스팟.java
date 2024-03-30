import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static boolean[][] visited;

    static class Node implements Comparable<Node>{
        int row;
        int col;
        int wall;

        Node(int row, int col, int wall) {
            this.row = row;
            this.col = col;
            this.wall = wall;
        }

        @Override
        public int compareTo(Node o) {
            return this.wall - o.wall;
        }
    }

    static void bfs(int row, int col) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(row, col, 0));
        visited[row][col] = true;

        Node node;
        int crow, ccol, mrow, mcol;
        while (!pq.isEmpty()) {
            node = pq.poll();
            crow = node.row;
            ccol = node.col;

            if (crow == N - 1 && ccol == M - 1) {
                System.out.println(node.wall);
                return;
            }

            for (int dir = 0; dir < 4; dir++) {
                mrow = crow + delR[dir];
                mcol = ccol + delC[dir];

                if (mrow < 0 || mcol < 0 || N <= mrow || M <= mcol || visited[mrow][mcol]) continue;

                visited[mrow][mcol] = true;

                if (map[mrow][mcol] == 1) pq.offer(new Node(mrow, mcol, node.wall + 1));
                else pq.offer(new Node(mrow, mcol, node.wall));
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        visited = new boolean[N][M];

        bfs(0, 0);

        br.close();
    }
}