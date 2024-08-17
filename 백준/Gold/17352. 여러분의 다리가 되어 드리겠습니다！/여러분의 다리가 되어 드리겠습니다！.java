import java.io.*;
import java.util.*;

public class Main {
    static int N;
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

        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        int a, b;
        for (int i = 0; i < N - 2; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int v1 = find(1), v2 = 0;
        for (int i = 2; i <= N; i++) {
            if (v1 != find(i)) {
                v2 = i;
                break;
            }
        }

        System.out.println(v1 + " " + v2);
    }
}