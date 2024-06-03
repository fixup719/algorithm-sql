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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[M][4];

        int v1, v2, num, cost;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            num = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            graph[i][0] = v1;
            graph[i][1] = v2;
            graph[i][2] = num;
            graph[i][3] = cost;
        }

        Arrays.sort(graph, (o1,o2)->{
            if (o1[2] == o2[2]) {
                return o1[3] - o2[3];
            } else {
                return o1[2] - o2[2];
            }
        });

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        StringBuilder ans1 = new StringBuilder();
        long ans2 = 0;
        int cnt = 0;
        for (int i = 0; i < M; i++) {
            v1 = graph[i][0];
            v2 = graph[i][1];
            num = graph[i][2];
            cost = graph[i][3];

            if (find(v1) == find(v2)) continue;

            union(v1, v2);

//            ans1 = ans1 * 10 + num;
            ans1.append(num);
            ans2 += cost;
            cnt++;
        }

        if (cnt != N - 1) System.out.println(-1);
        else System.out.println(ans1 + " " + ans2);
    }
}