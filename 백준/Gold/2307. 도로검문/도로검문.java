import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Node>[] lst;
    static int[] dist;
    static boolean[] visited;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static ArrayList<Integer> selected = new ArrayList<>();

    static class Node implements Comparable<Node> {
        int to;
        int cost;
        int num;

        Node(int to, int cost, int num) {
            this.to = to;
            this.cost = cost;
            this.num = num;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    static void daijk(int blockNum) {
        Arrays.fill(visited, false);
        Arrays.fill(dist, 1 << 30);
        dist[1] = 0;
        pq.offer(new Node(1, 0, 0));

        int cur, nxt, nc, num;
        while (!pq.isEmpty()) {
            cur = pq.poll().to;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : lst[cur]) {
                nxt = node.to;
                nc = dist[cur] + node.cost;
                num = node.num;

                // 검문 중인 도로면 pass
                if (num == blockNum) continue;

                if (dist[nxt] > nc) {
                    // 최단 경로
                    dist[nxt] = nc;
                    selected.add(num);
                    pq.offer(new Node(nxt, dist[nxt], num));
                }
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

        dist = new int[N + 1];
        visited = new boolean[N + 1];

        int a, b, c;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            lst[a].add(new Node(b, c, i));
            lst[b].add(new Node(a, c, i));
        }


        int max = 0, min = 1 << 30;
        daijk(0);
        max = Math.max(max, dist[N]);
        min = Math.min(min, dist[N]);

        for (int i = 0, size = selected.size(); i < size; i++) {
            daijk(selected.get(i));
            max = Math.max(max, dist[N]);
            min = Math.min(min, dist[N]);
        }

        if (max == 1 << 30) System.out.println(-1);
        else System.out.println(max - min);
    }
}