import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] lst;
    static boolean[] visited;

    static boolean isCycle(int cur, int prv) {
        visited[cur] = true;

        for (Integer nxt : lst[cur]) {
            if (nxt == prv) continue;

            if (visited[nxt]) return false;

            if (!isCycle(nxt, cur)) return false;

        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int a, b, tnum = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0) break;

            lst = new ArrayList[N + 1];
            for (int i = 0; i < N + 1; i++) {
                lst[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());

                lst[a].add(b);
                lst[b].add(a);
            }


            // 트리 판단하기
            // 1. 무 사이클
            // 2. 정점 개수 - 1 = 간선 개수
            visited = new boolean[N + 1];
            boolean ans; int cnt = 0;
            for (int i = 1; i <= N; i++) {
                if (visited[i]) continue;
                ans = isCycle(i, 0);
                if (ans) cnt++;
            }

            if (cnt == 0) {
                sb.append("Case ").append(tnum++).append(": No trees.\n");
            } else if (cnt == 1) {
                sb.append("Case ").append(tnum++).append(": There is one tree.\n");
            } else {
                sb.append("Case ").append(tnum++).append(": A forest of ").append(cnt).append(" trees.\n");
            }
        }

        System.out.println(sb);
    }
}