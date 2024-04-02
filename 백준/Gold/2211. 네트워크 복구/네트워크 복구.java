import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static ArrayList<Node>[] lists;
    static boolean[] visited;
    static int[] dist;
    static int[] parent;

    static class Node implements Comparable<Node> {
        int no;
        int weight;

        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }

    static void daijk(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        int cur, nxt, nd;
        while (!pq.isEmpty()) {
            cur = pq.poll().no;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : lists[cur]) {
                nxt = node.no;
                nd = dist[cur] + node.weight;

                if (dist[nxt] > nd) {
                    dist[nxt] = nd;
                    parent[nxt] = cur;
                }
                
                pq.offer(new Node(nxt, dist[nxt]));
            }
        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lists = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lists[i] = new ArrayList<>();
        }

        int A, B, C;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            lists[A].add(new Node(B, C));
            lists[B].add(new Node(A, C));
        }

        visited = new boolean[N + 1];
        parent = new int[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, 1 << 30);

        daijk(1);

        sb.append((N - 1) + "\n");
        for (int i = 2; i <= N; i++) {
            sb.append(i + " " + parent[i] + "\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}