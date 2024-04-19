import java.io.*;
import java.util.*;

public class Main {
    static int N, M, t;
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        graph = new int[M][3];

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

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

        int answer = 0;
        int mul = 0;
        for (int i = 0; i < M; i++) {
            v1 = graph[i][0];
            v2 = graph[i][1];
            c = graph[i][2];

            if (find(v1) == find(v2)) continue;

            union(v1, v2);
            answer += (c + t * mul++);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}