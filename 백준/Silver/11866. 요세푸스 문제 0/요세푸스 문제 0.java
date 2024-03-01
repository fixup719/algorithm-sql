import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        sb.append("<");

        st  = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        int nth = 0;
        while (!q.isEmpty()) {
            nth++;
            if (nth == K) {
                sb.append(q.poll());
                if (q.size() > 0) {
                    sb.append(", ");
                }
                nth = 0;
            } else {
                q.offer(q.poll());
            }
        }

        sb.append(">");
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}