import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[][] arr;
    static ArrayList<Node>[] lst;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static boolean[] visited;
    static int[] dist;

    // 기존 dp+dfs로 접근하면 메모리 초과 뜸 -> 각 칸을 노드라고 생각하고 최단경로 알고리즘으로 풀기

    static class Node implements Comparable<Node> {
        int no;
        int d;

        Node(int no, int d) {
            this.no = no;
            this.d = d;
        }

        @Override
        public int compareTo(Node node) {
            return this.d - node.d;
        }
    }

    static void daijk(int start) {
        pq.offer(new Node(start, 0));
        dist[start] = 0;


        int cur, nxt, nd;
        while (!pq.isEmpty()) {
            cur = pq.poll().no;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : lst[cur]) {
                nxt = node.no;
                nd = Math.max(dist[cur], node.d);

                dist[nxt] = Math.min(dist[nxt], nd);
                pq.offer(new Node(nxt, dist[nxt]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        lst = new ArrayList[N * N + 1];
        for (int i = 0; i < N * N + 1; i++) {
            lst[i] = new ArrayList<>();
        }
        visited = new boolean[N * N + 1];
        dist = new int[N * N + 1];
        Arrays.fill(dist, 1 << 30);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2차원을 1차원으로 펼치기 -> row*N+col+1
        // 인접칸 거리(경사 크기) 저장
        int from, to, mrow, mcol, dst;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                from = row * N + col + 1;

                for (int d = 0; d < 4; d++) {
                    mrow = row + delR[d];
                    mcol = col + delC[d];

                    if (mrow < 0 || mcol < 0 || N <= mrow || N <= mcol) continue;

                    to = mrow * N + mcol + 1;

                    dst = Math.abs(arr[row][col] - arr[mrow][mcol]);

                    lst[from].add(new Node(to, dst));
                }
            }
        }

        daijk(1);

        System.out.println(dist[N * N]);

        br.close();
    }
}