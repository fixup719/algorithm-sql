import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] human;
    static ArrayList<Integer>[] list;
    static int[][] dp;

    static int dfs(int cur, int prv) {
        dp[cur][0] = 0;
        dp[cur][1] = human[cur];
        int ret = human[1];
        for (Integer nxt : list[cur]) {
            if (nxt == prv) continue;

            dfs(nxt, cur);
            dp[cur][1] += dp[nxt][0];
            dp[cur][0] += Math.max(dp[nxt][0], dp[nxt][1]);
        }

        return ret;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        human = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            human[i] = Integer.parseInt(st.nextToken());
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

        dp = new int[N + 1][2];

        dfs(1, 0);

        bw.write(String.valueOf(Math.max(dp[1][0], dp[1][1])));
        bw.flush();
        bw.close();
        br.close();
    }
}
