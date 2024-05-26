import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
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

    static int daijk() {
        int ans = 0;
        int v1, v2, c;
        for (int i = 0; i < M + 1; i++) {
            v1 = graph[i][0];
            v2 = graph[i][1];
            c = graph[i][2];

            if (find(v1) == find(v2)) continue;

            union(v1, v2);
            ans += c;
        }

        return ans;
    }

    static void initParent() {
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[M + 1][3];
        parent = new int[N + 1];

        int v1, v2, c;
        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph[i][0] = v1;
            graph[i][1] = v2;
            graph[i][2] = c == 1 ? 0 : 1;
        }

        // 최선의 경우 찾기
        Arrays.sort(graph, (o1, o2) -> {
            return o1[2] - o2[2];
        });

        initParent();

        int ans1 = daijk();

        // 최악의 경우 찾기
        Arrays.sort(graph, (o1, o2) -> {
            return o2[2] - o1[2];
        });

        initParent();

        int ans2 = daijk();

        System.out.println(ans2 * ans2 - ans1 * ans1);
        
        br.close();
    }
}