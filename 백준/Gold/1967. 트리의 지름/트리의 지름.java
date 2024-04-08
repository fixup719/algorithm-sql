import java.io.*;
import java.util.*;

public class Main {
    static int N; // 노드 개수
    static ArrayList<Node>[] list; // 그래프 정보
    static int max, tmpNode; // 지름 max 값, 트리 지름에 속할 노드 번호

    static class Node {
        int no;
        int weight;

        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }

    static void dfs(int cur, int prv, int w) {

        if (max < w) {
            max = w;
            tmpNode = cur;
        }

        for (Node nxt : list[cur]) {
            if (nxt.no == prv) continue;

            dfs(nxt.no, cur, w + nxt.weight);
        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int node1, node2, weight;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            list[node1].add(new Node(node2, weight));
            list[node2].add(new Node(node1, weight));
        }

        dfs(1, 0, 0);
        dfs(tmpNode, 0, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}