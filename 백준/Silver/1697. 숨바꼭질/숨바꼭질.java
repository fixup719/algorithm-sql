import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static boolean[] visited;
    static Queue<Integer> q = new LinkedList<>();
    static int dist;

    static void bfs(int node) {
        q.offer(node);
        visited[node] = true;

        int cur, size;
        while (!q.isEmpty()) {
            size = q.size();

            for (int i = 0; i < size; i++) {
                cur = q.poll();

                if (cur == K) {
                    System.out.println(dist);
                    return;
                }

                if (0 <= cur - 1 && !visited[cur - 1]) {
                    q.offer(cur - 1);
                    visited[cur - 1] = true;
                }

                if (cur + 1 <= 100000 && !visited[cur + 1]) {
                    q.offer(cur + 1);
                    visited[cur + 1] = true;
                }

                if (2 * cur <= 100000 && !visited[2 * cur]) {
                    q.offer(2 * cur);
                    visited[2 * cur] = true;
                }
            }
            dist++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100010];

        bfs(N);
    }
}