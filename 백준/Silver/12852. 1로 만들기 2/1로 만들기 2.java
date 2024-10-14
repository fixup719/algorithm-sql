import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] log = new int[1_000_010];
    static Stack<Integer> stack = new Stack<>();

    static class Node implements Comparable<Node> {
        int no;
        int cnt;

        Node(int no, int cnt) {
            this.no = no;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node node) {
            return this.cnt - node.cnt;
        }
    }

    static int bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[1_000_010];
        pq.offer(new Node(N, 0));
        visited[N] = true;

        int cur, cn;
        while (!pq.isEmpty()) {
            cur = pq.peek().no;
            cn = pq.poll().cnt;

            if (cur == 1) return cn;

            if (cur % 3 == 0 && !visited[cur / 3]) {
                log[cur / 3] = cur;
                visited[cur / 3] = true;
                pq.offer(new Node(cur / 3, cn + 1));
            }

            if (cur % 2 == 0 && !visited[cur / 2]) {
                visited[cur / 2] = true;
                log[cur / 2] = cur;
                pq.offer(new Node(cur / 2, cn + 1));
            }

            if (cur - 1 > 0 && !visited[cur - 1]) {
                visited[cur - 1] = true;
                log[cur - 1] = cur;
                pq.offer(new Node(cur - 1, cn + 1));
            }

        }

        return -1;
    }

    static void dfs(int cur) {
        stack.push(cur);
        if (cur == N) return;
        dfs(log[cur]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int ret = bfs();
        dfs(1);
        StringBuilder sb = new StringBuilder();
        sb.append(ret).append("\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);
    }
}

