import java.io.*;
import java.util.*;

public class Main {
    /*

        소 방문은 홀스타인 1번소에서 시작, H번 소에서 끝나야 함
        또한 소를 방문할 때는 1...H, 1...G순서를 유지하며 방문
        모든 소를 방문하는 최소 에너지는 ?
        에너지 : 한 소에서 다른 소로 이동할 때 이동 거리 D의 제곱이 소모됨

     */
    static int H, G;
    static int[][] hols;
    static int[][] guern;
    static long[][][] dp;

    static long recur(int h, int g, int visit) {

        if ( h == H && g == G) {
            if (visit == 0) return 0;
            else {
                if (H == 1) {
                    return (long) (hols[1][0] - guern[g][0]) * (hols[1][0] - guern[g][0])
                            + (long) (hols[1][1] - guern[g][1]) * (hols[1][1] - guern[g][1]);
                }
                return Integer.MAX_VALUE;
            }
        }

        if (dp[h][g][visit] != -1) return dp[h][g][visit];

        int sx, sy;
        if (visit == 0) {
            // 이전에 홀스타인 말 방문
            sx = hols[h][0];
            sy = hols[h][1];
        } else {
            // 이전에 건스 말 방문
            sx = guern[g][0];
            sy = guern[g][1];
        }

        // 홀스타인 방문
        long ret = Integer.MAX_VALUE;
        long dist;
        if (h + 1 <= H) {
            dist = (long) (sx - hols[h + 1][0]) * (sx - hols[h + 1][0]) + (long) (sy - hols[h + 1][1]) * (sy - hols[h + 1][1]);
            ret = Math.min(ret, recur(h + 1, g, 0) + dist);
        }


        // 건스 방문
        if (g + 1 <= G) {
            dist = (long) (sx - guern[g + 1][0]) * (sx - guern[g + 1][0]) + (long) (sy - guern[g + 1][1]) * (sy - guern[g + 1][1]);
            ret = Math.min(ret, recur(h, g + 1, 1) + dist);
        }

        dp[h][g][visit] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        hols = new int[H + 1][2];
        guern = new int[G + 1][2];

        for (int i = 1; i <= H; i++) {
            st = new StringTokenizer(br.readLine());
            hols[i][0] = Integer.parseInt(st.nextToken());
            hols[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= G; i++) {
            st = new StringTokenizer(br.readLine());
            guern[i][0] = Integer.parseInt(st.nextToken());
            guern[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new long[H + 10][G + 10][2];
        for (long[][] d1 : dp) {
            for (long[] d2 : d1) {
                Arrays.fill(d2, -1);
            }
        }

        System.out.println(recur(1, 0, 0));

        br.close();
    }
}