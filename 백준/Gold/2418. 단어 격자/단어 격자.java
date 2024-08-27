import java.io.*;
import java.util.*;

public class Main {
    static int H, W, L;
    static char[][] graph;
    static int[] delR = {-1, 1, 0, 0, -1, -1, 1, 1}; // 상 하 좌 우 좌상 우상 좌하 우하
    static int[] delC = {0, 0, -1, 1, -1, 1, -1, 1};
    static String target;
    static long[][][] dp = new long[101][201][201];

    static long recur(int cur, int row, int col) {
        if (cur == target.length()) {
            return 1;
        }

        if (dp[cur][row][col] != -1) return dp[cur][row][col];

        long ret = 0;
        int mr, mc;
        for (int dir = 0; dir < 8; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];


            if (mr < 0 || mc < 0 || H <= mr || W <= mc) continue;
            if (graph[mr][mc] == target.charAt(cur)) ret += recur(cur + 1, mr, mc);
        }

        dp[cur][row][col] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        graph = new char[H][W];

        String input;
        for (int i = 0; i < H; i++) {
            input = br.readLine();
            for (int j = 0; j < W; j++) {
                graph[i][j] = input.charAt(j);
            }
        }
        target = br.readLine();

        for (long[][] d1 : dp) {
            for (long[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        long answer = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (graph[i][j] == target.charAt(0)) {
                    answer += recur(1, i, j);
                }
            }
        }

        System.out.println(answer);
    }
}