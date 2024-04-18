import java.io.*;
import java.util.*;

public class Main {
    static int n, to;
    static int[] parent;
    static int[] graph;

    static int find(int x) {
        if (parent[x] == x) return x;

        return find(parent[x]);
    }

    static void union(int from, int to) {
        from = find(from);
        to = find(to);

        if (from == to) return;

        parent[from] = to;
    }

    static int dfs(int cur, int start, int cnt) {

        if (graph[cur] == start) return cnt;

        return dfs(graph[cur], start, cnt + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            n = Integer.parseInt(br.readLine());

            parent = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                parent[i] = i;
            }

            graph = new int[n + 1];

            st = new StringTokenizer(br.readLine());
            int size = 0;
            for (int from = 1; from <= n; from++) {
                to = Integer.parseInt(st.nextToken());

                graph[from] = to;

                if (find(from) == find(to)) {
                    // 사이클 형성
                    // 해당 사이클의 노드 개수 구하기
                    size += dfs(from, from, 1);
                    continue;
                }

                union(from, to);
            }
            sb.append(n - size).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}