import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] lst = new ArrayList[3010];
    static boolean[] visited = new boolean[3010];
    static Set<Integer> isCycle = new TreeSet<>();

    static boolean dfs(int cur, int start, int depth) {
        visited[cur] = true;

        for (Integer nxt : lst[cur]) {

            if (nxt == start && depth > 2) {
                return true;
            }

            if (visited[nxt]) continue;

            if (dfs(nxt, start, depth + 1)) return true;
        }

        return false;
    }

    static int bfs(int v) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(v);
        visited[v] = true;

        int cur, size, dist = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cur = q.poll();

                if (isCycle.contains(cur)) {
                    return dist;
                }

                for (Integer nxt : lst[cur]) {
                    if (visited[nxt]) continue;

                    visited[nxt] = true;
                    q.add(nxt);
                }
            }

            dist++;
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < 3010; i++) {
            lst[i] = new ArrayList<>();
        }

        int v1, v2;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            lst[v1].add(v2);
            lst[v2].add(v1);
        }

        for (int i = 1; i <= N; i++) {
            if (dfs(i, i, 1)) isCycle.add(i);

            Arrays.fill(visited, false);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(bfs(i)).append(" ");
            Arrays.fill(visited, false);
        }

        System.out.println(sb);

        br.close();
    }
}