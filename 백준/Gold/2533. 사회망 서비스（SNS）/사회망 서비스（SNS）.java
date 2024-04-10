import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] list;
    static int cnt = 1;
    static int ans;
    static int[][] dp;

    // 각 노드마다 어답터인경우 아닌경우 고려하면서 탑다운 dp로 풀기

//    static int dfs(int cur, int prv, int prvIsAdapter) {
//
//        if (list[cur].isEmpty() && prvIsAdapter == 0) return 1;
//        else if (list[cur].isEmpty() && prvIsAdapter == 1) return 0;
//
//        int ret = 0;
//        for (Integer nxt : list[cur]) {
//            if (prv == nxt) continue;
//
//            if (prvIsAdapter == 0) {
//                ret += dfs(nxt, cur, 1) + 1;
//            } else {
//                ret += Math.min(dfs(nxt, cur, 0), dfs(nxt, cur, 1) + 1);
//            }
//        }
//
//        return ret;
//    }

    static void dfs(int cur, int prv) {
        dp[cur][0] = 0;
        dp[cur][1] = 1;

        for (Integer nxt : list[cur]) {
            if (nxt == prv) continue;

            dfs(nxt, cur);
            dp[cur][0] += dp[nxt][1];
            dp[cur][1] += Math.min(dp[nxt][0], dp[nxt][1]);
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

        int node1, node2;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            list[node1].add(node2);
            list[node2].add(node1);
        }

        dp = new int[N + 1][2];

        dfs(1, 0);

        bw.write(String.valueOf(Math.min(dp[1][0], dp[1][1])));
        bw.flush();
        bw.close();
        br.close();
    }
}
