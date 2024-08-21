import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int s, e;
        int[][] timeline = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            timeline[i][0] = s;
            timeline[i][1] = e;
        }

        Arrays.sort(timeline, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (!pq.isEmpty() && pq.peek() <= timeline[i][0]) {
                pq.poll();
                pq.offer(timeline[i][1]);
            } else {
                pq.offer(timeline[i][1]);
                answer++;
            }
        }

        System.out.println(answer);
    }
}