import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] pCnt;
    static int[] time;
    static ArrayList<Node>[] lst;

    static class Node implements Comparable<Node> {
        int no;
        int time;

        Node(int no, int time) {
            this.no = no;
            this.time = time;
        }

        @Override
        public int compareTo(Node node) {
            return this.time - node.time;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        pCnt = new int[N + 1];
        time = new int[N + 1];
        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        int p;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            while (true) {
                p = Integer.parseInt(st.nextToken());

                if (p == -1) break;

                pCnt[i]++;
                lst[p].add(new Node(i, time[i]));
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (pCnt[i] == 0) pq.offer(new Node(i, time[i]));
        }

        int cur, curTime, nxt;
        int[] ans = new int[N + 1];
        while (!pq.isEmpty()) {
            cur = pq.peek().no;
            curTime = pq.poll().time;

            ans[cur] = curTime;

            for (Node node : lst[cur]) {
                nxt = node.no;
                pCnt[nxt]--;

                if (pCnt[nxt] == 0) pq.offer(new Node(nxt, curTime + time[nxt]));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append("\n");
        }

        System.out.println(sb);
    }
}
