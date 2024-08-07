import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S, E;
    static ArrayList<Node>[] lst;
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    static void daijk() {
        Arrays.fill(dist, 1 << 30);
        dist[S] = 0;
        pq.offer(new Node(S, 0));

        int cur, nxt, nc;
        while (!pq.isEmpty()) {
            cur = pq.poll().to;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : lst[cur]) {
                nxt = node.to;
                nc = node.cost + dist[cur];

                dist[nxt] = Math.min(dist[nxt], nc);
                pq.offer(new Node(nxt, dist[nxt]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            lst[a].add(new Node(b, c));
            lst[b].add(new Node(a, c));
        }

        daijk();

        System.out.println(dist[E]);
    }
}