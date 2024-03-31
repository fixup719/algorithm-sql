import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] list;
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited;

    static int bfs(int node, int target) {
        q.offer(node);
        visited[node] = true;

        int cur, size, cnt = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int i = 0; i < size; i++) {
                cur = q.poll();

                if (cur == target){
                    q.clear();
                    return cnt;
                }


                for (Integer nxt : list[cur]) {
                    if (visited[nxt]) continue;

                    q.offer(nxt);
                    visited[nxt] = true;
                }
            }

            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int node1, node2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            list[node1].add(node2);
            list[node2].add(node1);
        }

        visited = new boolean[N + 1];
        int sum, ansNode = 0, ansSum = 1 << 30, tmp;
        for (int i = 1; i <= N; i++) {
            sum = 0;

            for (int j = 1; j <= N; j++) {
                if (i == j) continue;

                tmp = bfs(i, j);

                sum += tmp;

                Arrays.fill(visited, false);
            }

            if (ansSum > sum) {
                ansSum = sum;
                ansNode = i;
            }
        }

        bw.write(String.valueOf(ansNode));
        bw.flush();
        bw.close();
        br.close();
    }
}