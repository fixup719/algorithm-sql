import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static boolean[] visited;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void recur(int cur) {
        if (cur == N) {
            for (int i = 0; i < N; i++) {
                sb.append(selected[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            selected[cur] = i;
            recur(cur + 1);
            visited[i] = false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        selected = new int[N];
        recur(0);

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}