import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String str;
    static int[] parent;

    static void recur(int cur) {
        if (str.charAt(cur) == 'E' && cur + 1 < str.length() && find(cur) != find(cur + 1)) {
            union(cur, cur + 1);
            recur(cur + 1);
        } else if (0 <= cur - 1 && find(cur) != find(cur - 1)) {
            union(cur, cur - 1);
            recur(cur - 1);
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;

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

        N = Integer.parseInt(br.readLine());
        str = br.readLine();
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            recur(i);
        }

        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }
}