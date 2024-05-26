import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Node>[] arr;
    static int[][] dp;

    static class Node{
        int no;
        int dist;

        Node(int no, int dist) {
            this.no = no;
            this.dist = dist;
        }
    }

    static int recur(int cur, int cnt) {
        if (cnt == N) return 0;

        if (dp[cur][cnt] != -1) return dp[cur][cnt];

        int nxt, nd, ret = 1 << 30;
        for (Node node : arr[cur]) {
            nxt = node.no;
            nd = node.dist;

            ret = Math.min(ret, recur(nxt, cnt + 1) + nd);
        }

        dp[cur][cnt] = ret;
        return ret;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());

        arr = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        int d;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                d = Integer.parseInt(st.nextToken());

                if (d != 0) {
                    arr[i].add(new Node(j, d));
                }
            }
        }

       // 0번 가상노드 추가
        for (int i = 1; i <= N; i++) {
            arr[0].add(new Node(i, 0));
        }

        dp = new int[N + 10][N + 10];
        for (int[] d1 : dp) {
            Arrays.fill(d1, -1);
        }

        int ans = recur(0, 0);
        if (ans != 1 << 30) System.out.println(ans);
        else System.out.println(-1);
        
        br.close();
    }
}