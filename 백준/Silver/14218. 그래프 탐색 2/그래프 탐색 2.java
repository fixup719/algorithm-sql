import java.io.*;
import java.util.*;

public class Main {
    static int N, M, Q;
    static ArrayList<Node>[] lst;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static boolean[] visited;
    static int[] dist;

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

    static void daijk(int start) {
        Arrays.fill(visited,false);
        Arrays.fill(dist, 1 << 30);
        pq.clear();
        dist[start] = 0;
        pq.offer(new Node(1, 0));

        int cur, nxt, nc;
        while (!pq.isEmpty()) {
            cur = pq.poll().to;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node o : lst[cur]) {
                nxt = o.to;
                nc = dist[cur] + o.cost;

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

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];
        dist = new int[N + 1];

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            lst[a].add(new Node(b, 1));
            lst[b].add(new Node(a, 1));
        }

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int ret;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            lst[a].add(new Node(b, 1));
            lst[b].add(new Node(a, 1));

            daijk(1);

            for (int j = 1; j <= N; j++) {
                ret = dist[j];
                if (ret == 1 << 30) sb.append(-1).append(" ");
                else sb.append(ret).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}