import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] fruit;
    static ArrayList<Integer>[] list;
    static int max;
    static int nodeNo = 1;
    static int minNode = 1;

    static void dfs(int cur, int prv, int sum) {

        sum += fruit[cur];

        if (max <= sum) {
            if (max == sum) {
                nodeNo = Math.min(cur, nodeNo);
            } else {
                nodeNo = cur;
            }
            max = sum;
        }

        for (Integer nxt : list[cur]) {
            if (nxt == prv) continue;

            dfs(nxt, cur, sum);
        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        fruit = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            fruit[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int node1, node2;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            list[node1].add(node2);
            list[node2].add(node1);
        }

        // 트리의 지름으로!
        dfs(1, 0, 0);
        minNode = nodeNo;
        max = -1;
        dfs(nodeNo, 0, 0);
        minNode = Math.min(nodeNo, minNode);

        bw.write(max + " " + minNode);
        bw.flush();
        bw.close();
        br.close();
    }
}
