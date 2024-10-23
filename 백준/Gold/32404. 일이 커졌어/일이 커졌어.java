import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> odd = new PriorityQueue<>();
        PriorityQueue<Integer> even = new PriorityQueue<>(
                new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2 - o1;
                    }
                }
        );
        for (int i = 1; i <= N; i++) {
            if (N / 2 < i) odd.offer(i);
            else even.offer(i);
        }

        /*
        654
        321
        4 3 5 2 6 1 => 223
        4 1 5 2 6 3 => 165
        6 3 5 2 4 1 => 189
        6 1 5 2 4 3 => 151
         */

        StringBuilder sb = new StringBuilder();
        int i = 1;
        while (i <= N) {
            if (i % 2 == 1) sb.append(odd.poll()).append(" ");
            else sb.append(even.poll()).append(" ");
            i++;
        }

        System.out.println(sb);

    }
}

