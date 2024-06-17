import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] height;
    static ArrayList<Integer>[] graph;
    static int[] dp;

    static int recur(int cur) {
        if (graph[cur].isEmpty()) return 0;

        if (dp[cur] != -1) return dp[cur];

        int ret = 1;
        for (Integer nxt : graph[cur]) {
            ret = Math.max(ret, recur(nxt) + 1);
        }

        dp[cur] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        height = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            if (height[a] < height[b]) {
                graph[a].add(b);
            } else {
                graph[b].add(a);
            }
        }

        dp = new int[N + 10];

        StringBuilder sb = new StringBuilder();
        Arrays.fill(dp, -1);
        for (int i = 1; i <= N; i++) {
            sb.append(recur(i) + 1).append("\n");
        }

        System.out.println(sb);
    }
}