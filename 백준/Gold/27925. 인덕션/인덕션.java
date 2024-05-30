import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] foods;
    static int[][][][] dp;

    static int recur(int cur, int place1, int place2, int place3) {
        if (cur == N) return 0;

        if (dp[cur][place1][place2][place3] != -1) return dp[cur][place1][place2][place3];

        int ret = 1 << 30;
        int temp, cnt1, cnt2;
        int[] place = {place1, place2, place3};
        for (int i = 0; i < 3; i++) {
            temp = place[i];

            // + 버튼 누르기
            cnt1 = 0;
            while (place[i] != foods[cur]) {
                place[i]++;
                cnt1++;
                if (place[i] == 10) place[i] = 0;
            }
            ret = Math.min(ret, recur(cur + 1, place[0], place[1], place[2]) + cnt1);

            // - 버튼 누르기
            place[i] = temp;
            cnt2 = 0;
            while (place[i] != foods[cur]) {
                place[i]--;
                cnt2++;
                if (place[i] == -1) place[i] = 9;
            }
            ret = Math.min(ret, recur(cur + 1,  place[0], place[1], place[2]) + cnt2);

            place[i] = temp;
        }

        dp[cur][place1][place2][place3] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        foods = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            foods[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][10][10][10];
        for (int[][][] d1 : dp) {
            for (int[][] d2 : d1) {
                for (int[] d3 : d2) {
                    Arrays.fill(d3, -1);

                }
            }
        }

        System.out.println(recur(0,0,0,0));

        br.close();
    }
}