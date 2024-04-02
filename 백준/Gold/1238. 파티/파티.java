import java.io.*;
import java.util.*;


public class Main {
    static int N, M, X;
    static ArrayList<Node>[] list;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static boolean[] visited;
    static int[] back;
    static int[] go;

    static class Node implements Comparable<Node>{
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

    static void daijk(int start, int[] dist) {
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        int cur, nxt, nd;
        while (!pq.isEmpty()) {
            cur = pq.poll().no;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : list[cur]) {
                nxt = node.no;
                nd = dist[cur] + node.weight;

                dist[nxt] = Math.min(dist[nxt], nd);

                pq.offer(new Node(nxt, dist[nxt]));
            }

        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int from, to, weight;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            list[from].add(new Node(to, weight));
        }

        visited = new boolean[N + 1];

        back = new int[N + 1];
        Arrays.fill(back, 1 << 30);
        daijk(X, back);

        go = new int[N + 1];
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            Arrays.fill(go, 1 << 30);
            Arrays.fill(visited, false);
            daijk(i, go);

            answer = Math.max(answer, go[X] + back[i]);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}