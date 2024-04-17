import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Node>[] list;
    static int from, to, answer;

    static class Node{
        int no;
        int dist;

        Node(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }
    }

    static void dfs(int cur, int prv, int total) {

        if (cur == to) {
            answer = total;
            return;
        }

        for (Node node : list[cur]) {
            if (node.no == prv) continue;

            dfs(node.no, cur, total + node.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int v1, v2, d;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            list[v1].add(new Node(v2, d));
            list[v2].add(new Node(v1, d));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            answer = 0;
            dfs(from, 0, 0);
            sb.append(answer+"\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}