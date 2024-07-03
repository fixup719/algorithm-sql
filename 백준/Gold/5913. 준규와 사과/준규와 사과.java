import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static int[][] arr = new int[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int[] delR = {0, 0, -1, 1};
    static int[] delC = {-1, 1, 0, 0};


    static int recur(int jr, int jc, int hr, int hc, int remain) {
        if (jr == hr && jc == hc) {
            // 모든 사과를 수확하고 도착 위치가 같다면
            if (remain < 1) {
                return 1;
            }
            else return 0;
        }

        int ret = 0;

        int mjr, mjc, mhr, mhc;
        for (int jd = 0; jd < 4; jd++) {
            for (int hd = 0; hd < 4; hd++) {
                mjr = jr + delR[jd];
                mjc = jc + delC[jd];
                mhr = hr + delR[hd];
                mhc = hc + delC[hd];

                if (mjr < 0 || mjc < 0 || mhr < 0 || mhc < 0
                        || 5 <= mjr || 5 <= mjc || 5 <= mhr || 5 <= mhc) continue;

                if (visited[mjr][mjc] || visited[mhr][mhc]) continue;
                if (arr[mjr][mjc] == 1 || arr[mhr][mhc] == 1) continue;

                visited[mjr][mjc] = true;
                visited[mhr][mhc] = true;
                ret += recur(mjr, mjc, mhr, mhc, remain - 2);

                visited[mjr][mjc] = false;
                visited[mhr][mhc] = false;
            }
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        int r, c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;

            arr[r][c] += 1;
        }

        visited[0][0] = true;
        visited[4][4] = true;

        int ans = recur(0, 0, 4, 4, 25 - K - 2);
        System.out.println(ans);
    }
}
