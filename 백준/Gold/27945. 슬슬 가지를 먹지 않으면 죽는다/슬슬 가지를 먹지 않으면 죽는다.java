import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] graph;
    static int[] parent;

    static int find(int x) {
        if (x == parent[x]) return x;

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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[M][3];
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = c;
        }

        Arrays.sort(graph, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });


        int days = 1, cnt = 0;
        for (int i = 0; i < M; i++) {
            a = graph[i][0];
            b = graph[i][1];
            c = graph[i][2];

            if (days != c) break;
            if (find(a) == find(b)) continue;
            union(a, b);
            days++;
            cnt++;
            if (cnt == N - 1) break;
        }

        System.out.println(days);
    }
}

