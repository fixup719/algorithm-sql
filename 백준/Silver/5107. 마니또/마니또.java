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

        int tc = 1;
        StringBuilder sb = new StringBuilder();
        while (true) {
            N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            parent = new int[N];
            for (int i = 0; i < N; i++) {
                parent[i] = i;
            }

            StringTokenizer st;
            String a, b;
            Map<String, Integer> map = new TreeMap<>();
            for (int i = 0, idx  = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());

                a = st.nextToken();
                b = st.nextToken();

                if (!map.containsKey(a)) {
                    map.put(a, idx++);
                }

                if (!map.containsKey(b)) {
                    map.put(b, idx++);
                }

                union(map.get(a), map.get(b));
            }

            Set<Integer> set = new TreeSet<>();
            for (int i = 0; i < N; i++) {
                set.add(parent[i]);
            }

            sb.append(tc).append(" ").append(set.size()).append("\n");
            tc++;
        }

        System.out.println(sb);
        br.close();
    }
}