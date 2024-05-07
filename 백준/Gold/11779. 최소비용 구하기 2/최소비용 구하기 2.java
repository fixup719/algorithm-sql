import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Node>[] lst;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] dist;
    static boolean[] visited;
    static int start, end;
    static int[] parent;
    static Stack<Integer> stack = new Stack<>();

    static class Node implements Comparable<Node> {
        int no;
        int cost;

        Node(int no, int cost) {
            this.no = no;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    static void daijk(int v) {
        pq.offer(new Node(v, 0));

        int cur, nxt, nc;
        while (!pq.isEmpty()) {
            cur = pq.poll().no;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : lst[cur]) {
                nxt = node.no;
                nc = dist[cur] + node.cost;

                if (dist[nxt] > nc) {
                    dist[nxt] = nc;
                    parent[nxt] = cur;
                }

                pq.offer(new Node(nxt, dist[nxt]));
            }
        }
    }

    static void route(int v) {

        if (v == 0) return;

        stack.add(v);

        route(parent[v]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        dist = new int[N + 1];

        visited = new boolean[N + 1];

        parent = new int[N + 1];

        int v1, v2, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            lst[v1].add(new Node(v2, c));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Arrays.fill(dist, 1 << 30);
        dist[start] = 0;

        daijk(start);

        System.out.println(dist[end]);

        route(end);

        System.out.println(stack.size());

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }
        System.out.println(sb);

        br.close();
    }
}