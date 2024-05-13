import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static ArrayList<Node>[] lst;
    static int[] parent;
    static int[] depth;
    static int[] distFromRoot;

    static class Node {
        int no;
        int dist;

        Node(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }
    }

    // 트리 구성하기
    static void dfs(int cur, int prv, int dist) {
        int nxt, nd;
        for (Node node : lst[cur]) {
            nxt = node.no;
            nd = node.dist;

            if (prv == nxt) continue;

            // 깊이 갱신
            depth[nxt] = depth[cur] + 1;
            // 부모 노드 갱신
            parent[nxt] = cur;
            // 루트로부터 누적 거리 갱신
            distFromRoot[nxt] = dist + nd;
            dfs(nxt, cur, dist + nd);
        }
    }

    // 공통 조상 LCA 찾기
    static int lca(int v1, int v2) {
        // 깊이가 더 큰 쪽을 n2로 맞춰주기
        if (depth[v1] > depth[v2]) {
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
        }

        // 두 노드가 같은 깊이가 될 떄까지
        while (depth[v1] != depth[v2]) {
            v2 = parent[v2];
        }

        // LCA 찾기
        while (v1 != v2) {
            v1 = parent[v1];
            v2 = parent[v2];
        }

        return v1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        int v1, v2, d;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            lst[v1].add(new Node(v2, d));
            lst[v2].add(new Node(v1, d));
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
        depth = new int[N + 1];
        distFromRoot = new int[N + 1];

        // 루트가 1인 트리 구성
        dfs(1, 0, 0);

        M = Integer.parseInt(br.readLine());
        int lca, dist;
        for (int i = 0; i < M; i++) { // 1만
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            lca = lca(v1, v2);
            
            dist = (distFromRoot[v1] - distFromRoot[lca])
                    + (distFromRoot[v2] - distFromRoot[lca]);

            sb.append(dist).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}