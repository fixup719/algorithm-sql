import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int n, s, e;
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            arr[n][0] = s;
            arr[n][1] = e;
        }

        Arrays.sort(arr, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            else return o1[0] - o2[0];
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                }
        );

        int cnt = 1;
        pq.offer(arr[0][1]);
        for (int i = 1; i < N; i++) {
            if (!pq.isEmpty()) {
                if (arr[i][0] < pq.peek()) {
                    cnt++;
                } else {
                    pq.poll();
                }
            }
            pq.offer(arr[i][1]);
        }

        System.out.println(cnt);
    }
}