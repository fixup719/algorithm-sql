import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] planets;
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

        if (planets[b] <= parent[a]) {
            parent[b] = a;
            planets[a] += planets[b];
        } else {
            parent[a] = b;
            planets[b] += planets[a];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        planets = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            planets[i] = Integer.parseInt(br.readLine());
        }

        int a, b;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (find(a) != find(b)) {
                union(a, b);
            }
            sb.append(planets[find(a)]).append("\n");
        }

        System.out.println(sb);
    }
}

