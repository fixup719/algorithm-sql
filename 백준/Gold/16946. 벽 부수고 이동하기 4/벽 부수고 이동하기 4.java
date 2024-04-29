import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] parent;
    static int[] size;
    static boolean[] visited;
    static int[] delR = {0, 0, 1, -1};
    static int[] delC = {1, -1, 0, 0};

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

    static void dfs(int row, int col) {

        int mr, mc, v1 = M * row + col + 1, v2;
        visited[v1] = true;
        for (int dir = 0; dir < 4; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (mr < 0 || mc < 0 || N <= mr || M <= mc) continue;

            v2 = M * mr + mc + 1;
            if (visited[v2] || map[mr][mc] > 0) continue;

            if (find(v1) == find(v2)) continue;

            union(v1, v2);

            dfs(mr, mc);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        parent = new int[N * M + 1];
        for (int i = 0; i < N * M + 1; i++) {
            parent[i] = i;
        }

        size = new int[N * M + 1];
        Arrays.fill(size, 1);

        visited = new boolean[N * M + 1];

        // 인접한 0끼리 같은 연결요소로 묶고 사이즈 갱신
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 || visited[M * i + j + 1]) continue;

                dfs(i, j);
            }
        }

        // 벽마다 이동가능한 칸의 개수 구하기 => 인접한 연결요소들의 크기 + 1(자기자신)
        int sum, mi, mj, v;
        Set<Integer> set = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;

                sum = 1;
                for (int dir = 0; dir < 4; dir++) {
                    mi = i + delR[dir];
                    mj = j + delC[dir];

                    if (mi < 0 || mj < 0 || N <= mi || M <= mj) continue;

                    if (map[mi][mj] != 0) continue;

                    v = M * mi + mj + 1;
                    if (set.contains(find(v))) continue;

                    set.add(find(v));
                    sum += size[find(v)];
                }

                set.clear();
                map[i][j] = sum;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]%10);
            }
            sb.append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}