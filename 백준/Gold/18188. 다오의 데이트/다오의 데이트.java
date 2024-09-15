import java.io.*;
import java.util.*;

public class Main {
    static int H, W, N;
    static char[][] arr = new char[21][21];
    static char[][] cmd = new char[21][2];
    static int[] delR = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] delC = {0, 0, -1, 1};
    static char[] dir = {'W', 'S', 'A', 'D'};
    static boolean[][][] visited = new boolean[21][21][21];
    static int[] log = new int[21];
    static int sr, sc, er, ec;

    static int dfs(int cr, int cc, int nth) {
        if (cr == er && cc == ec) {
            N = nth - 1;
            return 1;
        }
        if (nth > N) return 0;

        int mr, mc, dir;
        for (int i = 0; i < 2; i++) {
            dir = cmd[nth][i];
            mr = cr + delR[dir];
            mc = cc + delC[dir];

            if (mr < 1 || mc < 1 || H < mr || W < mc
                    || arr[mr][mc] == '@' || visited[nth][mr][mc]) continue;

            visited[nth][mr][mc] = true;
            log[nth] = dir;
            if (dfs(mr, mc, nth + 1) > 0) return 1;
            visited[nth][mr][mc] = false;
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        String input;
        for (int i = 1; i <= H; i++) {
            input = br.readLine();
            for (int j = 1; j <= W; j++) {
                arr[i][j] = input.charAt(j - 1);
                if (arr[i][j] == 'D') {
                    sr = i;
                    sc = j;
                } else if (arr[i][j] == 'Z') {
                    er = i;
                    ec = j;
                }
            }
        }

        N = Integer.parseInt(br.readLine());
        char c;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                c = st.nextToken().charAt(0);
                if (c == 'W') cmd[i][j] = 0;
                else if (c == 'S') cmd[i][j] = 1;
                else if (c == 'A') cmd[i][j] = 2;
                else if (c == 'D') cmd[i][j] = 3;
            }
        }

        int ret = dfs(sr, sc, 1);

        if (ret == 1) {
            System.out.println("YES");
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= N; i++) {
                sb.append(dir[log[i]]);
            }
            System.out.println(sb);
        } else System.out.println("NO");
    }
}

