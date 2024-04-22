import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static ArrayList<int[]> graph;
    static int[] parent;
    static int[] size;

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
        size[a] += size[b];
    }

    static int makeMst() {
        int v1, v2, c, answer = 0;
        for (int i = 0; i < graph.size(); i++) {
            v1 = graph.get(i)[0];
            v2 = graph.get(i)[1];
            c = graph.get(i)[2];

            if (find(v1) == find(v2)) continue;

            union(v1, v2);
            answer += c;
        }

        return answer;
    }

    static void initParent() {
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
    }

    static boolean isAble() {
        return size[find(1)] == N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        int v1, v2;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            graph.add(new int[]{v1, v2, i});
        }

        Collections.sort(graph, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        parent = new int[N + 1];
        size = new int[N + 1];

        StringBuilder sb = new StringBuilder();
        int result;
        while (K-- > 0) {
            initParent();
            Arrays.fill(size, 1);

            result = makeMst();

            if (isAble()) sb.append(result).append(" ");
            else sb.append("0 ");

            graph.remove(0);
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}