import java.io.*;
import java.util.*;

public class Main {
    static int N, V, E, A, B;
    static int[] pos;
    static ArrayList<Node>[] lst;
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<Node>();

    static class Node implements Comparable<Node>{
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static void daijk(int start) {
        Arrays.fill(visited, false);
        Arrays.fill(dist, 1 << 30);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        int cur, nxt, nd;
        while (!pq.isEmpty()) {
            cur = pq.poll().to;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : lst[cur]) {
                nxt = node.to;
                nd = node.cost + dist[cur];

                dist[nxt] = Math.min(dist[nxt], nd);

                pq.offer(new Node(nxt, dist[nxt]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        pos = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }

        lst = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            lst[a].add(new Node(b, c));
            lst[b].add(new Node(a, c));
        }

        dist = new int[V + 1];
        visited = new boolean[V + 1];

        int sum = 0, ret;
        for (int i = 0; i < N; i++) {
            daijk(pos[i]);

            if(dist[A] == 1 << 30) dist[A] = -1;
            if(dist[B] == 1 << 30) dist[B] = -1;

            ret = dist[A] + dist[B];

            sum += ret;
        }

        System.out.println(sum);
    }
}