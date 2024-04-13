import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] list;
    static boolean[] defensed;
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

        // 내가 변호할 사람들을 담는당
        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            list[a].add(b);
        }

        // 단방향 간선 지우면서 변호 받은거 체크하기
        defensed = new boolean[N + 1];

        int v;
        for (int i = 1; i <= N; i++) {
            for (int j = 0, size = list[i].size(); j < size;) {
                v = list[i].get(j);
                if (!list[v].contains(i)) {
                    // 단방향
                    defensed[v] = true;

                    // 단방향 간선 제거
                    list[i].remove(Integer.valueOf(v));
                    size--;
                } else {
                    j++;
                }
            }
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        int to;
        for (int from = 1; from <= N; from++) {
            for (int j = 0; j < list[from].size(); j++) {
                to = list[from].get(j);
                list[to].remove(Integer.valueOf(from));

                if (find(to) == find(from)) {
                    // 사이클 형성
                    defensed[find(to)] = true;
                } else {
                    if (defensed[find(to)]) union(to, from);
                    else union(from, to);
                }
            }
        }


        // 가능한지 판별
        String answer = "YES";
        for (int i = 1; i <= N ; i++) {
            if (defensed[i]) continue;
            if (defensed[find(i)]) continue;
            answer = "NO";
        }

        bw.write(answer);
        bw.flush();
        bw.close();
        br.close();
    }
}