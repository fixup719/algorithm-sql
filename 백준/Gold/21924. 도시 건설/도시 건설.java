import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[] parent;
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
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        graph = new int[M][3];

        int a, b, c;
        long sum = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = c;
            sum += c;
        }

        Arrays.sort(graph, ((o1, o2) -> {
            return o1[2] - o2[2];
        }));

        long ans = 0;
        for (int i = 0; i < M; i++) {
            a = graph[i][0];
            b = graph[i][1];
            c = graph[i][2];

            if (find(a) == find(b)) continue;

            union(a, b);

            ans += c;
        }

        Set<Integer> set = new TreeSet<>();
        for (int i = 1; i <= N; i++) {
            set.add(find(i));
        }

        if (set.size() > 1) System.out.println(-1);
        else System.out.println(sum - ans);

        br.close();
    }
}