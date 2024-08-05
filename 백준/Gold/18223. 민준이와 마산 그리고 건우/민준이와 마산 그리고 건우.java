import java.io.*;
import java.util.*;

public class Main {
    static int V, E, P;
    static ArrayList<Node>[] lst;
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    static class Node implements Comparable<Node>{
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
        pq.offer(new Node(start, 0));

        int cur, nxt, nd;
        while (!pq.isEmpty()) {
            cur = pq.poll().to;


            if (visited[cur]) continue;

            visited[cur] = true;

            if (cur == V) return;

            for (Node node : lst[cur]) {
                nxt = node.to;
                nd = dist[cur] + node.cost;

                dist[nxt] = Math.min(dist[nxt], nd);

                pq.offer(new Node(nxt, dist[nxt]));
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

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

        Arrays.fill(dist, 1 << 30);
        dist[1] = 0;
        daijk(1);
        int dist1 = dist[V];
        int dist2 = dist[P];

        Arrays.fill(visited, false);
        Arrays.fill(dist, 1 << 30);
        dist[P] = 0;
        daijk(P);
        int dist3 = dist[V];

        if (dist1 == dist2 + dist3) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }
}