import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static char[][] map;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int sr, sc;

    static int recur(int cur, int cr, int cc) {
        if (cur == T) {
            return 0;
        }

        int mr, mc, ret = 0;
        for (int dir = 0; dir < 4; dir++) {
            mr = cr + delR[dir];
            mc = cc + delC[dir];

            if (mr < 0 || mc < 0 || R <= mr || C <= mc || map[mr][mc] == '#') continue;

            // 이동
            if (map[mr][mc] == 'S') {
                map[mr][mc] = '.';
                ret = Math.max(ret, recur(cur + 1, mr, mc) + 1);
                map[mr][mc] = 'S';
            } else {
                ret = Math.max(ret, recur(cur + 1, mr, mc));
            }
        }
        // 이동 X
        ret = Math.max(ret, recur(cur + 1, cr, cc));

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        String input;
        for (int i = 0; i < R; i++) {
            input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == 'G'){
                    sr = i;
                    sc = j;
                }
            }
        }

        int ans = recur(0, sr, sc);

        System.out.println(ans);
    }
}