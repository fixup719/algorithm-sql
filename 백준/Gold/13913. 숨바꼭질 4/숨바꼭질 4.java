import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static boolean[] visited;
    static int[] mul = {1, 1, 2};
    static int[] add = {1, -1, 0};
    static int[] log;
    static int dist;

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        int cur, nxt, size;
        while (!q.isEmpty()) {

            size = q.size();

            for (int i = 0; i < size; i++) {
                cur = q.poll();

                if (cur == K) return;

                // 1*cur+1
                // 1*cur-1
                // 2*cur+0
                for (int j = 0; j < 3; j++) {
                    nxt = mul[j] * cur + add[j];

                    if (nxt < 0 || 200000 < nxt || visited[nxt] ) continue;

                    q.offer(nxt);
                    visited[nxt] = true;
                    log[nxt] = cur;
                }
            }

            dist++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[200010];

        log = new int[200010];

        bfs(N);

        Stack<Integer> stack = new Stack<>();
        int end = K;
        while (true) {
            stack.add(end);

            if (end == N) break;

            end = log[end];
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0, size = stack.size(); i < size; i++) {
            sb.append(stack.pop() + " ");
        }

        bw.write(dist + "\n" + sb);
        bw.flush();
        bw.close();
        br.close();
    }
}