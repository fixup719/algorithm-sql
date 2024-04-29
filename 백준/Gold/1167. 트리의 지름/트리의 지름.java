import java.io.*;
import java.util.*;

public class Main {
    static int V;
    static ArrayList<Node>[] graph;
    static int max, idx;

    static class Node {
        int no;
        int cost;

        Node(int no, int cost) {
            this.no = no;
            this.cost = cost;
        }
    }

    static void dfs(int cur, int prv, int total) {

        if (max < total) {
            max = total;
            idx = cur;
        }

        for (Node nxt : graph[cur]) {
            if (nxt.no == prv) continue;

            dfs(nxt.no, cur, total + nxt.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        for (int i = 0; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int v1, v2, c;
        for (int i = 1; i <= V; i++) {
            st = new StringTokenizer(br.readLine());

            v1 = Integer.parseInt(st.nextToken());

            while (true) {
                v2 = Integer.parseInt(st.nextToken());

                if (v2 == -1) break;

                c = Integer.parseInt(st.nextToken());

                graph[v1].add(new Node(v2, c));
            }
        }

        dfs(1, 0, 0);
        max = 0;
        dfs(idx, 0, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}