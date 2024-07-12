import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static ArrayList<Integer>[] lst;
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited;

    static int bfs(int start) {
        q.offer(start);
        visited[start] = true;

        int cur, size, dist = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cur = q.poll();

                for (Integer nxt : lst[cur]) {
                    if (visited[nxt]) continue;

                    visited[nxt] = true;
                    q.offer(nxt);
                }
            }
            dist++;
        }

        return dist - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];

        int a, b;
        while (true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (a == -1) break;

            lst[a].add(b);
            lst[b].add(a);
        }

        int min = 1 << 30, ret;
        Queue<Integer> ans = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            ret = bfs(i);

            if (min > ret) {
                if (!ans.isEmpty()) ans.clear();

                ans.offer(i);
                min = ret;
            } else if (min == ret) {
                ans.offer(i);
            }

            Arrays.fill(visited, false);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(min).append(" ").append(ans.size()).append("\n");
        while (!ans.isEmpty()) {
            sb.append(ans.poll()).append(" ");
        }
        System.out.println(sb);
    }
}
