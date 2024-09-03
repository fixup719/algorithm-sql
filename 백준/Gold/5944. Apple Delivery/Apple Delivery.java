import java.io.*;
import java.util.*;

public class Main {
    static int C, P, PB, PA1, PA2;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Node>[] lst;
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

    static void daijk(int start) {
        Arrays.fill(dist, 1 << 30);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        int cur, nxt;
        while (!pq.isEmpty()) {
            cur = pq.poll().to;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : lst[cur]) {
                nxt = node.to;
                dist[nxt] = Math.min(dist[nxt], dist[cur] + node.cost);
                pq.offer(new Node(nxt, dist[nxt]));
            }
        }


        Arrays.fill(visited,false);
        pq.clear();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        PB = Integer.parseInt(st.nextToken());
        PA1 = Integer.parseInt(st.nextToken());
        PA2 = Integer.parseInt(st.nextToken());

        dist = new int[P + 1];
        visited = new boolean[P + 1];
        lst = new ArrayList[P + 1];
        for (int i = 0; i < P + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        int a, b, c;
        for (int i = 0; i < C; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            lst[a].add(new Node(b, c));
            lst[b].add(new Node(a, c));
        }


        // PB -> PA1 -> PA2
        // PB -> PA2 -> PA1

        int d1, d2, d3;
        daijk(PB);
        // PB <-> PA1
        d1 = dist[PA1];
        // PB <-> PA2
        d2 = dist[PA2];

        daijk(PA1);
        // PA1 <-> PA2
        d3 = dist[PA2];

        System.out.println(Math.min(d1 + d3, d2 + d3));
    }
}

