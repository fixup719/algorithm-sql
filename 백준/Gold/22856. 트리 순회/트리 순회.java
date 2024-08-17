import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Node[] tree;
    static int[] parent;
    static boolean[] visited;
    static int last;


    static class Node {
        int leftNode;
        int rightNode;

        Node(int leftNode, int rightNode) {
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    static public int search(int cur) {

        if (cur == last && visited[last]) return 0;

        int ret = 0;

        if (tree[cur].leftNode != -1 && !visited[tree[cur].leftNode]) {
            // 왼쪽 자식 노드가 존재하고, 방문하지 않았다면 해당 노드로 이동
            ret += search(tree[cur].leftNode) + 1;
        }

        if (tree[cur].rightNode != -1 && !visited[tree[cur].rightNode]) {
            // 오른쪽 자식 노드가 존재하고, 방문하지 않았다면 해당 노드로 이동
            ret += search(tree[cur].rightNode) + 1;
        }

        // 부모 노드로 이동
        visited[cur] = true;
        if (!visited[parent[cur]] && !visited[last]) ret += search(parent[cur]) + 1;

        return ret;
    }

    static void inorder(int v) {
        if (v == -1) return;

        inorder(tree[v].leftNode);
        last = v;
        inorder(tree[v].rightNode);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new Node[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
        visited = new boolean[N + 1];

        int a, b, c;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            tree[a] = new Node(b, c);
            if (b != -1) parent[b] = a;
            if (c != -1) parent[c] = a;
        }

        // 중위순회의 마지막 노드 찾기
        inorder(1);

        // 루트 노드는 항상 1번
        System.out.println(search(1));
    }
}