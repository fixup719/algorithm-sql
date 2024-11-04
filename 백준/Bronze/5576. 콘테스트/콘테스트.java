import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < 10; i++) {
            pq1.offer(Integer.parseInt(br.readLine()));
        }

        PriorityQueue<Integer> pq2 = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < 10; i++) {
            pq2.offer(Integer.parseInt(br.readLine()));
        }

        int s1 = 0, s2 = 0;
        for (int i = 0; i < 3; i++) {
            s1 += pq1.poll();
            s2 += pq2.poll();
        }

        System.out.println(s1 + " " + s2);
    }
}