import java.io.*;
import java.util.*;

public class Main {
    static int F, S, G, U, D;

    static int bfs() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                new Comparator<int[]>() {
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        return o1[1] - o2[1];
                    }
                }
        );
        boolean[] visited = new boolean[2_000_001];
        pq.offer(new int[]{S, 0});
        visited[S] = true;
        int cur, cnt, nxt;
        while (!pq.isEmpty()) {
            cur = pq.peek()[0];
            cnt = pq.poll()[1];

            if (cur == G) return cnt;

            // up 버튼
            nxt = cur + U;
            if (nxt <= F && !visited[nxt]) {
                visited[nxt] = true;
                pq.offer(new int[]{nxt, cnt + 1});
            }

            // down 버튼
            nxt = cur - D;
            if (1 <= nxt && !visited[nxt]) {
                visited[nxt] = true;
                pq.offer(new int[]{nxt, cnt + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int ret = bfs();

        if (ret != -1) System.out.println(ret);
        else System.out.println("use the stairs");
    }
}

