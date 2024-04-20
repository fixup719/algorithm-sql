import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<int[]> graph;
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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        String input;
        int total = 0;
        char alpha;
        for (int i = 1; i <= N; i++) {
            input = br.readLine();
            for (int j = 1; j <= N; j++) {
                alpha = input.charAt(j - 1);
                if (alpha == '0') continue;
                if (alpha - 'a' < 0) {
                    // 대문자
                    graph.add(new int[]{i, j, alpha - 'a' + 59});
                    total += alpha - 'a' + 59;
                } else {
                    // 소문자
                    graph.add(new int[]{i, j, alpha - 'a' + 1});
                    total += alpha - 'a' + 1;
                }
            }
        }

        Collections.sort(graph, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int answer = 0;
        int v1, v2, len;
        for (int i = 0; i < graph.size(); i++) {
            v1 = graph.get(i)[0];
            v2 = graph.get(i)[1];
            len = graph.get(i)[2];

            if (N > 1 && v1 == v2) continue;

            if (N > 1 && find(v1) == find(v2)) continue;

            answer += len;
            union(v1, v2);
        }

        boolean check = true;
        int p = find(1);
        for (int i = 2; i <= N; i++) {
            if (p != find(i)) {
                check = false;
            }
        }

        if (!check) bw.write("-1");
        else if (N == 1) bw.write(String.valueOf(answer));
        else bw.write(String.valueOf(total - answer));
        bw.flush();
        bw.close();
        br.close();
    }
}