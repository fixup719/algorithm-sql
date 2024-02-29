import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] cards;
    static boolean[] visited;
    static int[] selected;
    static Set<String> set = new TreeSet<>();

    static void recur(int cur) {

        if (cur == K) {

            String str = "";
            for (int i = 0; i < K; i++) {
                str += selected[i];
            }

            set.add(str);

            return;
        }

        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            selected[cur] = cards[i];

            recur(cur + 1);

            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        cards = new int[N];
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(br.readLine());
        }

        visited = new boolean[N];
        selected = new int[K];

        recur(0);

        bw.write(String.valueOf(set.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}