import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] cows;
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
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        cows = new int[N + 1][2];

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        int x, y;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            cows[i][0] = x;
            cows[i][1] = y;
        }

        graph = new ArrayList<>();
        int dist;
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;

                dist = (int) (Math.pow(cows[i][0] - cows[j][0], 2) + Math.pow(cows[i][1] - cows[j][1], 2));

                graph.add(new int[]{i, j, dist});
            }
        }

        Collections.sort(graph, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int a, b, c, nodeCnt = 1, answer = 0;
        for (int i = 0; i < graph.size(); i++) {
            a = graph.get(i)[0];
            b = graph.get(i)[1];
            c = graph.get(i)[2];

            if (find(a) == find(b)) continue;


            union(a, b);
            nodeCnt++;
            answer = c;

            if (nodeCnt == N) break;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}