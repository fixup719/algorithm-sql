import java.io.*;
import java.util.*;

public class Main {
    static int V, E, K;
    static ArrayList<Node>[] list;
    static boolean[] visited;
    static int[] dist;

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    static void dijk(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, dist[0]));

        int cur, nxt, nw;
        while (!pq.isEmpty()) {
            cur = pq.poll().to;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : list[cur]) {
                nxt = node.to;
                nw = dist[cur] + node.weight;

                dist[nxt] = Math.min(dist[nxt], nw);
                pq.offer(new Node(nxt, dist[nxt]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        list = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int v1, v2, w;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            // 방향 그래프이니까 하나만
            list[v1].add(new Node(v2, w));
        }

        dist = new int[V + 1];
        Arrays.fill(dist, 300010);
        dist[K] = 0;

        visited = new boolean[V + 1];

        dijk(K);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            if (dist[i] == 300010) sb.append("INF\n");
            else sb.append(dist[i]+"\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}