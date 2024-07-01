import java.io.*;
import java.util.*;

public class Main {
    static int N;
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

        N = Integer.parseInt(br.readLine());

        graph = new int[N * N - N][3];

        int cost, idx = 0;
        for (int from = 0; from < N; from++) {
            st = new StringTokenizer(br.readLine());
            for (int to = 0; to < N; to++) {
                cost = Integer.parseInt(st.nextToken());

                if (from == to) continue;

                graph[idx][0] = from;
                graph[idx][1] = to;
                graph[idx++][2] = cost;
            }
        }

        Arrays.sort(graph, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        long ans = 0;
        int a, b, c;
        for (int i = 0; i < graph.length; i++) {
            a = graph[i][0];
            b = graph[i][1];
            c = graph[i][2];

            if (find(a) == find(b)) continue;

            union(a, b);
            ans += c;
        }

        System.out.println(ans);

    }
}
