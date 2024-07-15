import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static ArrayList<Integer>[] lst;
    static int[] pCnt;
    static int[] time;

    static class Node implements Comparable<Node> {
        int no;
        int time;

        Node(int no, int time) {
            this.no = no;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        pCnt = new int[N + 1];
        time = new int[N + 1];
        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        int t, p;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());

            time[i] = t;
            pCnt[i] = p;
            for (int j = 0; j < p; j++) {
                lst[Integer.parseInt(st.nextToken())].add(i);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (pCnt[i] == 0) pq.offer(new Node(i, time[i]));
        }

        int cur, pTime = 0;
        boolean[] v = new boolean[N + 1];
        while (!pq.isEmpty()) {
            cur = pq.peek().no;
            pTime = pq.poll().time;

            if (v[cur]) continue;

            v[cur] = true;

            for (Integer nxt : lst[cur]) {
                if (v[nxt]) continue;
                pCnt[nxt]--;

                if (pCnt[nxt] == 0) {
                    pq.offer(new Node(nxt, pTime + time[nxt]));
                }
            }
        }

        System.out.println(pTime);

    }
}