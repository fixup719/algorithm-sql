import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] graph;
    static final int INF = 1_000_000_010;

    static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // i -> j로 가능 경우와
                    // i -> k -> j로 가는 경우 비교
                    // 최솟값으로 갱신
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 자기 자신으로 연결되는 경우가 아닐 경우 INF로 초기화
                if (i != j) graph[i][j] = INF;
            }
        }

        int a, b, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());

            // 도로가 여러 개 존재 가능 -> 가장 최소 경로로 갱신
            graph[a][b] = Math.min(graph[a][b], c);
        }

        floyd();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == INF) System.out.print(0 + " ");
                else System.out.print(graph[i][j] + " ");
            }
            System.out.println();
        }
    }
}