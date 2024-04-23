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

        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        parent = new int[N * M + 1];
        for (int i = 0; i < N * M + 1; i++) {
            parent[i] = i;
        }

        int v1, v2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                v1 = j + i * M + 1;

                if (map[i][j] == 'N') {
                    v2 = j + (i - 1) * M + 1;
                } else if (map[i][j] == 'W') {
                    v2 = j - 1 + i * M + 1;
                } else if (map[i][j] == 'S') {
                    v2 = j + (i + 1) * M + 1;
                } else {
                    v2 = j + 1 + i * M + 1;
                }

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