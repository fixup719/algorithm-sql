import java.io.*;
import java.util.*;

public class Main {
    static int N, M, p, q;
    static int[] parent = new int[10010];
    static int[][] graph;

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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());

            for (int i = 0; i < 10010; i++) {
                parent[i] = i;
            }

            graph = new int[M][3];
            int v1, v2, c;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                v1 = Integer.parseInt(st.nextToken());
                v2 = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                graph[i][0] = v1;
                graph[i][1] = v2;
                graph[i][2] = c;
            }

            Arrays.sort(graph, (o1,o2)->{
                return o1[2] - o2[2];});

            boolean check = false;
            for (int i = 0; i < M; i++) {
                v1 = graph[i][0];
                v2 = graph[i][1];

                if (find(v1) == find(v2)) continue;

                union(v1, v2);

                if (v1 == p && v2 == q) check = true;
            }

            if (!check) sb.append("NO\n");
            else sb.append("YES\n");

        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}