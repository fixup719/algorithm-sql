import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] lists = new ArrayList[10010];
    static int[] dist = new int[10010];
    static boolean[] visited = new boolean[10010];

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

    static void daijk(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        int cur, nxt, nc;
        while (!pq.isEmpty()) {
            cur = pq.poll().no;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : lists[cur]) {
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
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int n, d, c, a, b, s, cnt, time;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            for (int i = 0; i < lists.length; i++) {
                lists[i] = new ArrayList<>();
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());

                lists[b].add(new Node(a, s));
            }

            Arrays.fill(dist, 1 << 30);
            Arrays.fill(visited, false);

            dist[c] = 0;

            daijk(c);

            cnt = 0;
            time = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i] != 1 << 30){
                    cnt++;
                    time = Math.max(dist[i], time);
                }
            }

            sb.append(cnt).append(" ").append(time).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}