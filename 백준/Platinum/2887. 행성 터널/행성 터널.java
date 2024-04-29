import java.io.*;
import java.util.*;

public class Main {
    static int N, idx;
    static Planet[] planets;
    static Node[] graph;
    static int[] parent;

    static class Node{
        int v1;
        int v2;
        int c;

        Node(int v1, int v2, int c) {
            this.v1 = v1;
            this.v2 = v2;
            this.c = c;
        }
    }

    static class Planet{
        int no;
        int x;
        int y;
        int z;

        Planet(int no, int x, int y, int z) {
            this.no = no;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static void compare(char s) {
        Arrays.sort(planets, new Comparator<Planet>() {
            @Override
            public int compare(Planet o1, Planet o2) {
                if (s == 'x') return o1.x - o2.x;
                else if(s == 'y') return o1.y - o2.y;
                else return o1.z - o2.z;
            }
        });
    }

    static void makeGraph(char s) {
        int dist;
        for (int i = 0; i < N - 1; i++) {
            if (s == 'x') dist = Math.abs(planets[i].x - planets[i + 1].x);
            else if (s == 'y') dist = Math.abs(planets[i].y - planets[i + 1].y);
            else dist = Math.abs(planets[i].z - planets[i + 1].z);

            graph[idx++] = new Node(planets[i].no, planets[i + 1].no, dist);
        }
    }

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

        planets = new Planet[N];
        graph = new Node[3 * (N - 1)];

        int x, y, z;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            z = Integer.parseInt(st.nextToken());

            planets[i - 1] = new Planet(i, x, y, z);
        }

        // x좌표 기준 정렬
        compare('x');
        // 각 행성별 가장 인접한 행성과의 x좌표 거리 담기
        makeGraph('x');

        // y좌표 기준 정렬
        compare('y');
        // 각 행성별 가장 인접한 행성과의 y좌표 거리 담기
        makeGraph('y');

        // z좌표 기준 정렬
        compare('z');
        // 각 행성별 가장 인접한 행성과의 z좌표 거리 담기
        makeGraph('z');

        // 비용 기준 graph 배열 정렬
        Arrays.sort(graph, (o1, o2) -> {
            return o1.c - o2.c;});

        // 연결요소 루트 노드 담을 배열 정렬
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        // 크루스칼 알고리즘
        int v1, v2, c, answer = 0;
        for (int i = 0; i < graph.length; i++) {
            v1 = graph[i].v1;
            v2 = graph[i].v2;
            c = graph[i].c;

            if (find(v1) == find(v2)) continue;

            union(v1, v2);
            answer += c;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}