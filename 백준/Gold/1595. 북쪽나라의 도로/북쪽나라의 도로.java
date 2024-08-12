import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] lst = new ArrayList[10001];
    static int max, maxNode;

    static class Node {
        int to;
        int cost;

        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static void dfs(int cur, int prv, int dist) {

        if (max < dist) {
            max = dist;
            maxNode = cur;
        }

        for (Node nxt : lst[cur]) {
            if (nxt.to == prv) continue;

            dfs(nxt.to, cur, dist + nxt.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10001; i++) {
            lst[i] = new ArrayList<>();
        }

        int a = -1, b, c;
        try {
            while (true) {
                st = new StringTokenizer(br.readLine());

                if (st.hasMoreTokens()) {
                    a = Integer.parseInt(st.nextToken());
                    b = Integer.parseInt(st.nextToken());
                    c = Integer.parseInt(st.nextToken());

                    lst[a].add(new Node(b, c));
                    lst[b].add(new Node(a, c));
                } else {
                    break;
                }
            }
        } catch (Exception e) {}

        if (a == -1) {
            System.out.println(0);
        } else {
            dfs(1, 0, 0);
            max = 0;
            dfs(maxNode, 0, 0);

            System.out.println(max);
        }


    }
}