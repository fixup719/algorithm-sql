import java.io.*;
import java.util.*;

/*
[문제요약]
- 맥세권 : 집 ~ 맥도날드 최단 거리 x이하
- 스세권 : 집 ~ 스타벅스 최단 거리 y이하
- 원하는 집 : 맥세권과 스세권을 만족하는 집 중 최단 거리 합이 최소

정점 개수 : V 3 ~ 1만
도로 개수 : E 0 ~ 30만
E개의 줄에 각 도로를 나타내는 세 개의 정수 u, v, w가 주어짐 w : ~1만
  정점 u v 사이에 가중치 w인 도로가 존재
    두 정점 사이에는 여러 간선이 존재 가능
E+2번째 줄에는 맥도날드 수 M, 맥세권 조건 x가 주어짐 x : ~1억
다음 줄에 M개의 맥도날드 정점 번호
E+4번째 줄에는 스타벅스 수 S, 스세권 조건 y가 주어짐 y : ~1억
다음 줄에 S개의 스타벅스 정점 번호

한 정점에 맥도날드와 스타벅스가 같이 위치 가능
집이 있는 정점에는 맥도날드나 스타벅스 X

원하는 집의 맥도날드까지의 최단거리와 스타벅스까지의 최단거리 합을 출력
만일 원하는 집이 존재하지 않으면 -1 출력
 */

public class Main {
    static int V, E, M, X, S, Y;
    static ArrayList<Node>[] graph;
    static ArrayList<Integer> mcdonalds = new ArrayList<>();
    static ArrayList<Integer> starbucks = new ArrayList<>();
    static int[] mdist;
    static int[] sdist;
    static boolean[] visited;

    static class Node implements Comparable<Node>{
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static void dijk(ArrayList<Integer> lst, int[] dist){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int v;
        for (int i = 0; i < lst.size(); i++) {
            v = lst.get(i);
            pq.offer(new Node(v, 0));
            dist[v] = 0;
        }

        int cur, nxt, nd;
        while (!pq.isEmpty()) {
            cur = pq.poll().to;

            if (visited[cur]) continue;

            visited[cur] = true;

            for (Node node : graph[cur]) {
                nxt = node.to;
                nd = dist[cur] + node.cost;

                dist[nxt] = Math.min(dist[nxt], nd);
                pq.offer(new Node(nxt, dist[nxt]));
            }
        }

        Arrays.fill(visited, false);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int a, b, c;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
            graph[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            mcdonalds.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            starbucks.add(Integer.parseInt(st.nextToken()));
        }

        mdist = new int[V + 1];
        sdist = new int[V + 1];
        visited = new boolean[V + 1];

        Arrays.fill(mdist, 1 << 30);
        Arrays.fill(sdist, 1 << 30);

        dijk(mcdonalds, mdist);
        dijk(starbucks, sdist);

        int ans = 1 << 30;
        for (int i = 1; i <= V; i++) {
            if (mdist[i] == 0 || sdist[i] == 0) continue;
            if (mdist[i] > X || sdist[i] > Y) continue;

            ans = Math.min(mdist[i] + sdist[i], ans);
        }

        if (ans == 1 << 30) System.out.println(-1);
        else System.out.println(ans);
    }
}