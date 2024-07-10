import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        int turn = 0, num;
        StringBuilder sb = new StringBuilder();
        while (q.size() > 1) {
            num = q.poll();
            if (turn % 2 == 0) {
                sb.append(num).append(" ");
            } else {
                q.offer(num);
            }
            turn++;
        }

        sb.append(q.poll());

        System.out.println(sb);
    }
}
