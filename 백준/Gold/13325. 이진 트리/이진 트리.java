import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;

public class Main {
//    static ArrayList<Node>[] tree;
//
//    static class Node {
//        int no;
//        int weight;
//
//        Node(int no, int weight) {
//            this.no = no;
//            this.weight = weight;
//        }
//    }
//
//    static void makeTree() {
//        // 트리 만들기
//        int startIdx = 1;
//        int lastIdx;
//        int v = 1;
//        int nodeNo = 2;
//        for (int h = 1; h <= K; h++) {
//            lastIdx = startIdx + (int) Math.pow(2, h) - 1;
//
//            for (int j = startIdx, k = 0; j <= lastIdx; j++) {
//                tree[v].add(new Node(nodeNo++, w[j]));
//                k++;
//                if (k % 2 == 0) v++;
//            }
//
//            startIdx = lastIdx + 1;
//        }
//    }

    static int K;
    static int[] w;
    static int answer;

    static int dfs(int node) {

        if (node * 2 >= w.length - 1) {
            answer += w[node];
            return w[node];
        }

        int leftNode = dfs(node * 2);
        int rightNode = dfs(node * 2 + 1);

        answer += w[node] + Math.abs(leftNode - rightNode);
        return w[node] + Math.max(leftNode, rightNode);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 타입 주의 -> 간선 총 합 구할 때 long타입으로 할 것

        // 일단 루트노드부더 리프노드까지의 합계를 모두 구해보자!
        // 높이별로 간선 묶음은 다음과 같이 끊기
        // 1, 2 / 3, 4, 5, 6 / 7, 8, 9, 10, 11, 12, 13 ,14 / 15, 16, .... , 30 /
        // lastIdx =  startIdx + pow(2, 높이) - 1
        // startIdx = lastIdx + 1 (초기값 1)
        // 경로 묶기
        // 1 - 3 - 7 - 15 ..
        // 1 - 3 - 8 -
        // 트리를 그냥 만들까.. ? 😢 어렵다

        K = Integer.parseInt(br.readLine());

        w = new int[(int) Math.pow(2, K + 1)];

        st = new StringTokenizer(br.readLine());
        for (int i = 2; i < w.length; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }
//
//        tree = new ArrayList[(int)Math.pow(2, K + 1)];
//        for (int i = 0; i < tree.length; i++) {
//            tree[i] = new ArrayList<>();
//        }
//        트리 만들 필요 없다 ㅠㅠㅠ 바보같다.. 그냥 맨 리프노드부터 간선 두개씩 비교하면서 같게 맞춰준다...
//        makeTree();

        // 간선이랑 노드랑 따로 구분해서 생각하니까 어려웠던거 같다.
        // 그냥 간선을 노드라고 생각하고 풀자
        // 각 노드마다 차이값만큼 더해주며 올라간다.
        // 이때 올라갈때 자식 노드(왼,오)의 차이값을 더해줬기 때문에 결국,
        // 왼쪽 자식 노드와 오른쪽 자식 노드 중 큰 쪽의 값을 따라간거다. 그래서
        // 둘 중 최댓값만큼 더해주고 위로 올려 보낸다.

        dfs(1);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}