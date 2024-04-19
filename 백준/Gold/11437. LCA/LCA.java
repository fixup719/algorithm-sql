import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] parent;
    static ArrayList<Integer> list1 = new ArrayList<>();
    static ArrayList<Integer> list2 = new ArrayList<>();
    static int[] count;


    static void dfs(int cur, int prv) {
        for (Integer nxt : graph[cur]) {
            if (nxt == prv) continue;

            parent[nxt] = cur;
            dfs(nxt, cur);
        }
    }

    static void findParent(int cur, ArrayList<Integer> list) {
        list.add(cur);
        if(parent[cur] == cur) return;

        findParent(parent[cur], list);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        count = new int[N + 1];

        int v1, v2;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        dfs(1, 0);

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            findParent(v1, list1);
            findParent(v2, list2);

            for (int i = 0, size = list1.size(); i < size; i++) {
                count[list1.get(i)]++;
            }

            for (int i = 0, size = list2.size(); i < size; i++) {
                if (count[list2.get(i)] != 0) {
                    sb.append(list2.get(i)).append("\n");
                    break;
                }
            }

            list1.clear();
            list2.clear();
            Arrays.fill(count, 0);

        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}