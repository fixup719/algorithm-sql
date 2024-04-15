import java.io.*;
import java.util.*;

public class Main {
    static int N, E;
    static ArrayList<Node>[] list;
    static int u, v;
    static int[] dist;
    static boolean[] visited;

    static class Node implements Comparable<Node> {
        int no;
        int cost;

        Node(int no, int cost) {
            this.no = no;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static void dijks(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        int cur, nxt, nc;
        while (!pq.isEmpty()) {
            cur = pq.poll().no;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : list[cur]) {
                nxt = node.no;
                nc = dist[cur] + node.cost;

                dist[nxt] = Math.min(dist[nxt], nc);
                pq.offer(new Node(nxt, dist[nxt]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int v1, v2, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            list[v1].add(new Node(v2, c));
            list[v2].add(new Node(v1, c));
        }

        st = new StringTokenizer(br.readLine());
        u = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());


        int stou, stov, utov, vtou, vtoe, utoe;

        dist = new int[N + 1];
        Arrays.fill(dist, 1 << 30);
        visited = new boolean[N + 1];
        dijks(1);
        stou = dist[u];
        stov = dist[v];

        Arrays.fill(visited, false);
        Arrays.fill(dist, 1 << 30);
        dijks(u);
        utov = dist[v];
        utoe = dist[N];

        Arrays.fill(visited, false);
        Arrays.fill(dist, 1 << 30);
        dijks(v);
        vtou = dist[u];
        vtoe = dist[N];

        int dist1 = stou + utov + vtoe;
        int dist2 = stov + vtou + utoe;

        boolean check1 = true;
        boolean check2 = true;

        if (stou == 1 << 30 || utov == 1 << 30 || vtoe == 1 << 30) {
            check1 = false;
        }

        if (stov == 1 << 30 || vtou == 1 << 30 || utoe == 1 << 30) {
            check2 = false;
        }

        if (!check1 && check2) {
            bw.write(String.valueOf(dist2));
        } else if (check1 && !check2) {
            bw.write(String.valueOf(dist1));
        } else if (check1 && check2) {
            bw.write(String.valueOf(Math.min(dist1, dist2)));
        } else {
            bw.write("-1");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}