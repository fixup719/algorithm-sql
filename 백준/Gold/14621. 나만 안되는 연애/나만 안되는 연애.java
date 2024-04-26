import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[] info;
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        info = new char[N + 1];
        for (int i = 1; i < N + 1; i++) {
            info[i] = st.nextToken().charAt(0);
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        graph = new int[M][3];
        int v1, v2, d;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            graph[i][0] = v1;
            graph[i][1] = v2;
            graph[i][2] = d;
        }

        Arrays.sort(graph, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int cnt = 1, answer = 0;
        for (int i = 0; i < M; i++) {
            v1 = graph[i][0];
            v2 = graph[i][1];
            d = graph[i][2];

            if (find(v1) == find(v2)) continue;

            if (info[v1] == info[v2]) continue;

            union(v1, v2);
            cnt++;
            answer += d;
        }

        if (cnt < N) bw.write("-1");
        else bw.write(String.valueOf(answer));

        bw.flush();
        bw.close();
        br.close();
    }
}