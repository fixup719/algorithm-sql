import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Node>[] list;
    static int[] parent;
    static int root;
    static int[] hinge;

    static class Node {
        int no;
        int weight;

        Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }

    // 힌지 등급 구하기
    static void getHinge(int cur, int prv, int w) {

        hinge[cur] = w;

        for (Node nxt : list[cur]) {
            if (prv == nxt.no) continue;

            getHinge(nxt.no, cur, w + nxt.weight);

            hinge[cur] += hinge[nxt.no] - w;
        }
    }

    // unladen load 구하기
    static int getLoad(int node, int sum) {
        sum += hinge[node];

        if (node == root) return sum;

        return getLoad(parent[node], sum);
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

        parent = new int[N + 1];
        int thisNode, parNode, weight;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            thisNode = Integer.parseInt(st.nextToken());
            parNode = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());

            list[parNode].add(new Node(thisNode, weight));
            parent[thisNode] = parNode;
        }

        for (int i = 1; i < N + 1; i++) {
            if (parent[i] == 0) {
                root = i;
                break;
            }
        }

        hinge = new int[N + 1];
        getHinge(root, 0, 0);

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(getLoad(i, 0), max);
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
        br.close();
    }
}
