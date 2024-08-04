import java.io.*;
import java.util.*;

public class Main {
    static int n,m, r;
    static int[][] graph;
    static int[] items;
    static final int INF = 1_000_000_000;

    static void floyd() {
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) graph[i][j] = INF;
            }
        }
        items = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        int a, b, l;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            l = Integer.parseInt(st.nextToken());

            graph[a][b] = Math.min(graph[a][b], l);
            graph[b][a] = Math.min(graph[b][a], l);
        }

        floyd();

        int sum, max = 0;
        for (int i = 0; i < n; i++) {
            sum = 0;
            for (int j = 0; j < n; j++) {
                if (graph[i][j] <= m) sum += items[j];
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}