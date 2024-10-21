import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static ArrayList<Integer>[] lst;
    static PriorityQueue<int[]> pq = new PriorityQueue<>(
            new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            }
    );
    static boolean[] visited;
    static int[] dist;

    static void daijk() {
        pq.offer(new int[]{X, 0});
        dist[X] = 0;

        int cur, nc;
        while (!pq.isEmpty()) {
            cur = pq.poll()[0];

            if (visited[cur]) continue;
            visited[cur] = true;

            for (Integer nxt : lst[cur]) {
                nc = dist[cur] + 1;

                dist[nxt] = Math.min(dist[nxt], nc);
                pq.offer(new int[]{nxt, dist[nxt]});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, 1 << 30);

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            lst[a].add(b);
        }

        daijk();

        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 1; i <= N; i++) {
            if (dist[i] == K) {
                flag = true;
                sb.append(i).append("\n");
            }
        }

        if (!flag) System.out.println(-1);
        else System.out.println(sb);
    }
}

