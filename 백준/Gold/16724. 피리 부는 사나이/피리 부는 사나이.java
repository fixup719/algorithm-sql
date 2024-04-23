import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
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

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        parent = new int[N * M + 10];
        for (int i = 0; i < N * M + 10; i++) {
            parent[i] = i;
        }

        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        // 연결 요소 만들기
        // 노드 번호 => i*M + j + 1;
        char dir;
        int v1, v2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dir = map[i][j];
                v1 = i * M + j + 1;

                if (dir == 'U') v2 = (i - 1) * M + j + 1;
                else if (dir == 'D') v2 = (i + 1) * M + j + 1;
                else if (dir == 'L') v2 = i * M + (j - 1) + 1;
                else v2 = i * M + (j + 1) + 1;

                if (find(v1) == find(v2)) continue;

                union(v1, v2);
            }
        }

        Set<Integer> set = new TreeSet<>();
        for (int i = 1; i <= N * M; i++) {
            set.add(find(i));
        }

        bw.write(String.valueOf(set.size()));
        bw.flush();
        bw.close();
        br.close();
    }
}