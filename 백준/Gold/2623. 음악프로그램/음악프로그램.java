import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
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

        int k, p, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            p = 0;
            for (int j = 0; j < k; j++) {
                c = Integer.parseInt(st.nextToken());

                if (p != 0){
                    pCnt[c]++;
                    lst[p].add(c);
                }

                p = c;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (pCnt[i] == 0) q.offer(i);
        }

        int cur;
        Queue<Integer> ans = new LinkedList<>();
        while (!q.isEmpty()) {
            cur = q.poll();
            ans.offer(cur);

            for (Integer nxt : lst[cur]) {
                pCnt[nxt]--;

                if (pCnt[nxt] <= 0) q.offer(nxt);
            }
        }

        if (ans.size() < N) System.out.println("0");
        else {
            StringBuilder sb = new StringBuilder();
            while (!ans.isEmpty()) {
                sb.append(ans.poll()).append("\n");
            }
            System.out.println(sb);
        }
    }
}
