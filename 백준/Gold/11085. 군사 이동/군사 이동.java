import java.io.*;
import java.util.*;

public class Main {
    static int p, w, c, v;
    static int[][] graph;
    static int[] parent;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        c = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());

        graph = new int[w][3];
        parent = new int[p];
        for (int i = 0; i < p; i++) {
            parent[i] = i;
        }

        int a, b, dist;
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());

            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = dist;
        }

        Arrays.sort(graph, (o1, o2) -> {
            return o2[2] - o1[2];
        });

        int ans = 0;
        for (int i = 0; i < w; i++) {
            a = graph[i][0];
            b = graph[i][1];
            dist = graph[i][2];

            if (find(a) == find(b)) continue;

            union(a, b);
            ans = dist;

            if (find(c) == find(v)) break;
        }

        System.out.println(ans);
    }
}
