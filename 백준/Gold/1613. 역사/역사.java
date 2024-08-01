import java.io.*;
import java.util.*;

public class Main {
    static int N, K, S;
    static int[][] dist;
    static final int INF = 1_000_000_010;

    static void floyd() {
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) dist[i][j] = INF;
            }
        }

        int p, c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;

            dist[p][c] = Math.min(dist[p][c], 1);
        }

        floyd();

        S = Integer.parseInt(br.readLine());
        int a, b;
        StringBuilder sb = new StringBuilder();
        while (S-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;

            if (dist[a][b] == INF){
                if (dist[b][a] == INF) sb.append(0);
                else sb.append(1);
            }
            else {
                sb.append(-1);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}