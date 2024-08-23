import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] pos;
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        parent[b] = a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pos = new int[N + 1][2];
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int[][] x = new int[N + 1][2];
        int[][] y = new int[N + 1][2];
        for (int i = 0; i < N + 1; i++) {
            x[i][0] = 1 << 30;
            y[i][0] = 1 << 30;
        }

        int p;
        for (int i = 1; i <= N; i++) {
            p = find(i);
            x[p][0] = Math.min(pos[i][0], x[p][0]);
            x[p][1] = Math.max(pos[i][0], x[p][1]);
            y[p][0] = Math.min(pos[i][1], y[p][0]);
            y[p][1] = Math.max(pos[i][1], y[p][1]);
        }

        int ans = 1 << 30;
        for (int i = 1; i <= N; i++) {
            p = find(i);
            ans = Math.min(ans, 2 * (x[p][1] - x[p][0] + y[p][1] - y[p][0]));
        }
        System.out.println(ans);
    }
}