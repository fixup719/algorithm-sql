import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] list;
    static int[] lower;
    static Queue<Integer> q = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        lower = new int[N + 1];

        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            list[from].add(to);

            lower[to]++;
        }

        for (int i = 1; i <= N ; i++) {
            if (lower[i] == 0) q.offer(i);
        }

        StringBuilder sb = new StringBuilder();

        int v;
        while (!q.isEmpty()) {
            v = q.poll();

            for (Integer nxt : list[v]) {
                lower[nxt]--;

                if (lower[nxt] == 0) q.offer(nxt);
            }

            sb.append(v).append(" ");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}