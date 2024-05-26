import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] pCnt = new int[N + 1];
        ArrayList<Integer>[] lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        int p, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            lst[p].add(c);
            pCnt[c]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        for (int i = 1; i <= N; i++) {
            if (pCnt[i] == 0) pq.offer(i);
        }

        int cur;
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            cur = pq.poll();

            sb.append(cur).append(" ");

            for (Integer nxt : lst[cur]) {
                pCnt[nxt]--;

                if (pCnt[nxt] == 0) pq.offer(nxt);
            }
        }

        System.out.println(sb);

        br.close();
    }
}