//
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] cost = new int[310];
    static int[] parent = new int[310];
    static boolean[] isWater = new boolean[310];
    static ArrayList<int[]> graph = new ArrayList<>();

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

        // 우물파는 비용이 가장 적은 우물 찾으며 비용 입력 받기
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(br.readLine());
        }

        graph = new ArrayList<>();

        int c;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                c = Integer.parseInt(st.nextToken());

                if (i == j) {
                    graph.add(new int[]{i, 0, cost[j]});
                    graph.add(new int[]{0, i, cost[j]});
                } else {
                    graph.add(new int[]{i, j, c});
                }
            }
        }

        // 비용 기준 오름차순 정렬
        Collections.sort(graph, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        // 부모 배열 초기화(자기 자신)
        for (int i = 0; i < 310; i++) {
            parent[i] = i;
        }

        // 크루스칼 알고리즘
        int v1, v2;
        int answer = 0;
        for (int i = 0; i < graph.size(); i++) {
            v1 = graph.get(i)[0];
            v2 = graph.get(i)[1];
            c = graph.get(i)[2];

            if (find(v1) == find(v2)) continue;

            union(v1, v2);
            answer += c;
        }

        System.out.println(answer);

        br.close();
    }
}