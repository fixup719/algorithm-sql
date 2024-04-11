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

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new int[N][3];

        parent = new int[2 * N + 10];
        for (int i = 0; i < 2 * N + 10; i++) {
            parent[i] = i;
        }

        int cost, v1, v2, v3, v4;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cost = Integer.parseInt(st.nextToken());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            v3 = Integer.parseInt(st.nextToken());
            v4 = Integer.parseInt(st.nextToken());

            union(v1, v2);
            union(v3, v4);
            graph[i][0] = cost;
            graph[i][1] = v1;
            graph[i][2] = v3;
        }

        Arrays.sort(graph, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        int c, a, b;
        int answer = 0;
        for (int i = 0; i < N; i++) {
            c = graph[i][0];
            a = graph[i][1];
            b = graph[i][2];

            if (find(a) == find(b)) continue;

            union(a, b);
            answer += c;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}