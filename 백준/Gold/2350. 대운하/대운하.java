import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] arr;
    static int[] parent;
    static ArrayList<Node>[] lst;
    static int start, end, ans;

    static class Node{
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int find(int x) {
        if (parent[x] == x) return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        parent[b] = a;
    }

    static void dfs(int cur, int prv, int minC) {

        if (cur == end) ans = minC;

        int nxt, nc;
        for (Node node : lst[cur]) {
            nxt = node.to;
            nc = node.cost;

            if (prv == nxt) continue;

            dfs(nxt, cur, Math.min(minC, nc));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[M][3];

        int v1, v2, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            arr[i][0] = v1;
            arr[i][1] = v2;
            arr[i][2] = c;
        }

        Arrays.sort(arr, (o1,o2)->{
            return o2[2] - o1[2];});

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            v1 = arr[i][0];
            v2 = arr[i][1];
            c = arr[i][2];

            if (find(v1) == find(v2)) continue;

            union(v1, v2);
            lst[v1].add(new Node(v2, c));
            lst[v2].add(new Node(v1, c));
        }

        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            ans = 210;
            dfs(start, 0, 210);
            sb.append(ans).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}