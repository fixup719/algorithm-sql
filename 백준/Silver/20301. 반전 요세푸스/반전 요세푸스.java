import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            dq.offer(i);
        }

        int nth = 1, cnt = 0;
        boolean flag = false;
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            if (nth == K) {
                if (!flag) sb.append(dq.pollFirst()).append("\n");
                else sb.append(dq.pollLast()).append("\n");
                nth = 0;
                cnt++;
            } else {
                if (!flag) dq.offerLast(dq.pollFirst());
                else dq.offerFirst(dq.pollLast());
            }

            if (cnt == M) {
                cnt = 0;
                flag = !flag;
            }

            nth++;
        }

        /*
            3 4 5

         */

        System.out.println(sb);
    }
}

