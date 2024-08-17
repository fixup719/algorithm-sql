import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
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

    static boolean isTree() {
        boolean ret = true;

        int p = find(1);
        for (int i = 2; i <= N; i++) {
            if (p != find(i)) {
                ret = false;
                break;
            }
        }

        return ret && (M == N - 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            parent = new int[N + 1];
            for (int i = 0; i < N + 1; i++) {
                parent[i] = i;
            }

            int a, b;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            if (isTree()) sb.append("tree\n");
            else sb.append("graph\n");
        }

        System.out.println(sb);
    }
}