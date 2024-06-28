import java.io.*;
import java.util.*;

/*
[문제요약]
경로표 출력하기
겨로표는 한 집하장에서 다른 집하장으로 최단경로로 이동 시 가장 먼저 거쳐야하는 집하장 번호 표시
예를들어 4행 5열은 4번 집하장에서 6번 집하장으로 최단 경로 이동시 가장 먼저 접하는 집하장 번호 의미

집하장 개수 N, 경로 개수 M
N <= 200이하 자연수, M <= 10000이하 자연수
M개의 줄에 경로로 이어진 집하정 번호와 비용이 주어진다.
 */

public class Main {
    static int N, M;
    static ArrayList<Node>[] graph;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static boolean[] visited;
    static int[] dist;
    static int[] entry;

    static class Node implements Comparable<Node>{
        int no;
        int dist;

        Node(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node node) {
            return this.dist - node.dist;
        }
    }

    static void dijk(int start) {
        dist[start] = 0;
        pq.offer(new Node(start, 0));

        int cur, nxt, nc;
        while (!pq.isEmpty()) {
            cur = pq.poll().no;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : graph[cur]) {
                nxt = node.no;
                nc = dist[cur] + node.dist;

                if (dist[nxt] > nc) {
                    dist[nxt] = nc;

                    if (cur == start) {
                        entry[nxt] = nxt;
                    } else {
                        entry[nxt] = entry[cur];
                    }

                }

                pq.offer(new Node(nxt, dist[nxt]));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        visited = new boolean[N + 1];
        dist = new int[N + 1];
        entry = new int[N + 1];

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            Arrays.fill(visited, false);
            Arrays.fill(dist, 1 << 30);
            Arrays.fill(entry, 0);

            dijk(i);

            for (int j = 1; j <= N; j++) {
                if (entry[j] == 0) sb.append("-").append(" ");
                else sb.append(entry[j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
