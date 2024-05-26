import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] lst;
    static int[] parent;
    static int[] depth;

    static void makeTree(int cur, int prv) {
        for (Integer nxt : lst[cur]) {
            if (nxt == prv) continue;

            depth[nxt] = depth[cur] + 1;
            parent[nxt] = cur;
            makeTree(nxt, cur);
        }
    }

    static int findLca(int v1, int v2) {
        if (depth[v1] > depth[v2]) {
            int tmp = v1;
            v1 = v2;
            v2 = tmp;
        }

        while (depth[v1] != depth[v2]) {
            v2 = parent[v2];
        }

        while (v1 != v2) {
            v1 = parent[v1];
            v2 = parent[v2];
        }

        return v1;
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

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        depth = new int[N + 1];

        int p;
        for (int i = 2; i <= N; i++) {
            p = Integer.parseInt(br.readLine());
            lst[p].add(i);
            lst[i].add(p);
        }

        makeTree(1, 0);

        StringBuilder sb = new StringBuilder();
        int v1, v2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            sb.append(findLca(v1, v2)).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}