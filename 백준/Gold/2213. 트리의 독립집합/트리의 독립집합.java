import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] arr;
    static ArrayList<Integer>[] lst;
    static int[] selected;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    });

    static int dfs(int cur, int prv, int pSelected) {
        // pSelected가 1이란거는 이전 prv 노드가 선택된 상태라는 뜻

        // 가상노드, 루트노드가 아니면서 연결 노드가 1개밖에 없다면 -> 리프노드
        if (1 < cur && lst[cur].size() < 2) return 0;

        int sum = 0, ret1 = 0, ret2 = 0;
        for (Integer nxt : lst[cur]) {
            if (nxt == prv) continue;

            if (pSelected == 1) {
                // 이전 인접 노드가 선택 된 경우 -> nxt 선택 불가
                sum += dfs(nxt, cur, 0);
            } else {
                // 이전 인점 노드가 선택 되지 않은 경우
                // nxt 노드 선택
                ret1 = dfs(nxt, cur, 1) + arr[nxt];

                // nxt 노드 선택X
                ret2 = dfs(nxt, cur, 0);

                if (ret1 < ret2) {
                    selected[nxt] = 0;
                } else {
                    selected[nxt] = 1;
                }
                sum += Math.max(ret1, ret2);
            }
        }

        return sum;
    }

    static void findNode(int cur, int prv, int isSel) {

        if (isSel == 1) {
            pq.offer(cur);
            for (Integer nxt : lst[cur]) {
                if (prv == nxt) continue;

                findNode(nxt, cur, 0);
            }
        } else {
            for (Integer nxt : lst[cur]) {
                if (prv == nxt) continue;

                findNode(nxt, cur, selected[nxt]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        // 가상 노드 추가 (0번 노드 -> 1번 노드)
        lst[0].add(1);

        int v1, v2;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            lst[v1].add(v2);
            lst[v2].add(v1);
        }

        selected = new int[N + 1];

        sb.append(dfs(0, 0,0)).append("\n");

        findNode(1, 0,selected[1]);

        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }

        System.out.println(sb);

        br.close();
    }
}