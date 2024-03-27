import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[] cost;
    static int[][] relation;
    static boolean[] visited;
    static int min;

    static void dfs(int node) {
        visited[node] = true;
        min = Math.min(min, cost[node]);

        for (int i = 1; i <= N; i++) {
            if (visited[i] || relation[node][i] != 1) continue;

            dfs(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cost = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        relation = new int[N + 1][N + 1];
        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            relation[a][b] = 1;
            relation[b][a] = 1;
        }

        visited = new boolean[N + 1];
        int total = 0;
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                min = 1 << 30;
                dfs(i);
                total += min;
            }
        }

        if (total <= K) bw.write(String.valueOf(total));
        else bw.write("Oh no");
        bw.close();
        br.close();
    }
}