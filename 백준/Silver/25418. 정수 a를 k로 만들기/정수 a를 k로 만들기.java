import java.io.*;
import java.util.*;

public class Main {
    static int A, K;

    static int bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1] - o2[1];
                    }
                }
        );
        boolean[] visited = new boolean[1_000_010];
        pq.offer(new int[] {A, 0});
        visited[A] = true;
        int cur, cn;
        while (!pq.isEmpty()) {
            cur = pq.peek()[0];
            cn = pq.poll()[1];

            if (cur == K) return cn;

            // 연산 1
            if (cur + 1 <= K && !visited[cur + 1]) {
                visited[cur + 1] = true;
                pq.offer(new int[] {cur + 1, cn + 1});
            }

            // 연산 2
            if (cur * 2 <= K && !visited[cur * 2]) {
                visited[cur * 2] = true;
                pq.offer(new int[] {cur * 2, cn + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int ret = bfs();
        System.out.println(ret);
    }
}

