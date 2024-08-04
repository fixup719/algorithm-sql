import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] graph;
    static int[] pos;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) graph[i][j] = INF;
            }
        }

        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());

            graph[a][b] = Math.min(graph[a][b], c);
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        K = Integer.parseInt(br.readLine());
        pos = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            pos[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        int min = 1 << 30, max;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            max = 0;
            for (int j = 0; j < K; j++) {
                max = Math.max(max, graph[i][pos[j]] + graph[pos[j]][i]);
            }
            if (min > max) {
                ans.clear();
                ans.add(i + 1);
                min = max;
            } else if (min == max){
                ans.add(i + 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}