import java.io.*;
import java.util.*;

/*
    N개의 도시가 동->서로 순서대로 위치
    제일 동쪽이 1번, 제일 서쪽이 N번
    M개 이하 도시를 지나는 여행을 계획할 때, 1번부터 시작해서 N번 도시에서 끝날 것
        출발지와 도착지도 M개 도시에 포함
    무조건 서쪽으로 번호가 증가하는 순서대로만 이동 가능
    이때, 비행항로가 개설된 도시간 움직일 수 있으며,
        최대한 맛있는 기내식만 먹음
    이때, 먹게되는 기내식 점수 총 합이 최대가 되는 경우는?

    도시 개수 N, 방문 도시 M, 개설된 항공로 개수 K
    K개 줄에 a b c  :a -> b 항로 존재, 기내식 점수 c

 */

public class Main {
    static int N, M, K;
    static ArrayList<Node>[] lst;
    static int[][] dp;

    static class Node {
        int to;
        int score;

        Node(int to, int score) {
            this.to = to;
            this.score = score;
        }
    }

    static int recur(int cur, int remain) {
        if (remain < 0) return Integer.MIN_VALUE;

        if (cur == N) {
            return 0;
        }

        if (dp[cur][remain] != -1) return dp[cur][remain];

        int ret =  Integer.MIN_VALUE;

        for (Node nxt : lst[cur]) {
            if (nxt.to < cur) continue;
            ret = Math.max(ret, recur(nxt.to, remain - 1) + nxt.score);
        }

        dp[cur][remain] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        int a, b, c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            lst[a].add(new Node(b, c));
        }

        dp = new int[310][310];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(1, M-1);

        System.out.println(ans);
    }
}