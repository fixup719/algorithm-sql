import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static ArrayList<Node>[] lst;
    static boolean[] visted;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int[] dist;
    static int[] dp;

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

    static int recur(int cur) {
        if (cur == 2) return 1;

        if (dp[cur] != -1) return dp[cur];

        int nxt, ret = 0;
        for (Node node : lst[cur]) {
            nxt = node.to;

            if (dist[cur] > dist[nxt]) {
                ret += recur(nxt);
            }
        }

        dp[cur] = ret;
        return ret;
    }

    static void daijk(int start) {
        Arrays.fill(dist, 1 << 30);
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        int cur, nxt, nc;
        while (!pq.isEmpty()) {
            cur = pq.poll().to;

            if (visted[cur]) continue;

            visted[cur] = true;

            for (Node node : lst[cur]) {
                nxt = node.to;
                nc = dist[cur] + node.cost;

                if (dist[nxt] > nc) {
                    dist[nxt] = nc;
                    pq.offer(new Node(nxt, dist[nxt]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }
        visted = new boolean[N + 1];
        dist = new int[N + 1];
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        int a, b, c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            lst[a].add(new Node(b, c));
            lst[b].add(new Node(a, c));
        }

        // 역방향 다익스트라... 왜 이생각을 못했지?
        daijk(2);

        int ans = recur(1);

        System.out.println(ans);
    }
}