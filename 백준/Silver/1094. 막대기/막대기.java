import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int X = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                }
        );
        pq.offer(64);
        int sum = 64, min;
        while (X < sum) {
            min = pq.poll();
            sum -= min;
            sum += min / 2;
            pq.offer(min / 2);
            if (X > sum) {
                pq.offer(min / 2);
                sum += min / 2;
            }
        }

        System.out.println(pq.size());
    }
}

