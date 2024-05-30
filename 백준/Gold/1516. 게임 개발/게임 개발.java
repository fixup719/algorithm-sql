import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        ArrayList<Integer>[] lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }
        int[] pCnt = new int[N + 1];

        int t, p;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            t = Integer.parseInt(st.nextToken());
            arr[i] = t;

            while (true) {
                p = Integer.parseInt(st.nextToken());

                if (p == -1) break;

                pCnt[i]++;
                lst[p].add(i);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[1] - o2[1];
            }
        });
        for (int i = 1; i <= N; i++) {
            if (pCnt[i] == 0) {
                pq.offer(new int[]{i, arr[i]});
            }
        }

        int cur, curTime;
        int[] ans = new int[N + 1];
        while (!pq.isEmpty()) {
            cur = pq.peek()[0];
            curTime = pq.poll()[1];

            ans[cur] = curTime;


            for (Integer nxt : lst[cur]) {
                pCnt[nxt]--;

                if (pCnt[nxt] == 0) {
                    pq.offer(new int[]{nxt, arr[nxt] + curTime});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append("\n");
        }

        System.out.println(sb);


        br.close();
    }
}