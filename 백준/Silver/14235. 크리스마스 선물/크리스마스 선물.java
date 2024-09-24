import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        StringBuilder sb = new StringBuilder();
        int a, v;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());

            if (a == 0) {
                if (!pq.isEmpty()) sb.append(pq.poll()).append("\n");
                else sb.append("-1\n");
            } else {
                for (int i = 0; i < a; i++) {
                    v = Integer.parseInt(st.nextToken());
                    pq.offer(v);
                }
            }
        }

        System.out.println(sb);
    }
}

