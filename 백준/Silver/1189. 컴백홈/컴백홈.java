import java.io.*;
import java.util.*;

public class Main {
    static int R, C, K;
    static char[][] map;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static boolean[][] visited;

    static int dfs(int row, int col, int dist) {
        if (dist > K) return 0;
        if (row == 0 && col == C - 1) {
            if (K == dist) return 1;
            else return 0;
        }

        int mr, mc, ret = 0;
        for (int dir = 0; dir < 4; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (mr < 0 || mc < 0 || R <= mr || C <= mc
                    || map[mr][mc] == 'T' || visited[mr][mc]) continue;

            visited[mr][mc] = true;
            ret += dfs(mr, mc, dist + 1);
            visited[mr][mc] = false;
        }


        return ret;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        String input;
        for (int i = 0; i < R; i++) {
            input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        visited[R - 1][0] = true;
        int ret = dfs(R - 1, 0, 1);

        System.out.println(ret);
    }
}

