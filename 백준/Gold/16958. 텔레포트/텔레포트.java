import java.io.*;
import java.util.*;

public class Main {
    static int N, T, M;
    static int[][] city;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        city = new int[N + 1][3];
        dist = new int[N + 1][N + 1];

        int s, x, y;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            city[i][0] = s;
            city[i][1] = x;
            city[i][2] = y;
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                    continue;
                }
                dist[i][j] = Math.abs(city[i][1] - city[j][1]) + Math.abs(city[i][2] - city[j][2]);

                if (city[i][0] == 1 && city[j][0] == 1) {
                    dist[i][j] = Math.min(dist[i][j], T);
                }
            }
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        int from, to;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            sb.append(dist[from][to]).append("\n");
        }

        System.out.println(sb);
    }
}

