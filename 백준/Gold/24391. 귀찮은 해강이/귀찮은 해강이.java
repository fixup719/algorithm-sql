import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) return parent[x];

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

        st = new StringTokenizer(br.readLine());
        int pre = Integer.parseInt(st.nextToken()), cur, cnt = 0;
        for (int i = 1; i < N; i++) {
            cur = Integer.parseInt(st.nextToken());

            if (find(cur) != find(pre)) {
                cnt++;
            }
            pre = cur;
        }

        System.out.println(cnt);
    }
}