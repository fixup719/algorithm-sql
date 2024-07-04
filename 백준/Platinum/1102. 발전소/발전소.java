import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int min;
    static int[][] dp;

    static int recur(int remain, int state) {
        if (remain == 0) return 0;

        if (dp[remain][state] != -1) return dp[remain][state];

        int ret = 1 << 30;
        for (int i = 0; i < N; i++) {
            if ((state & (1 << i)) == 0) continue;

            for (int j = 0; j < N; j++) {
                if ((state & (1 << j)) != 0) continue;

                ret = Math.min(ret, recur(remain - 1, state | (1 << j)) + arr[i][j]);

            }
        }

        dp[remain][state] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String str = br.readLine();
        min = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'Y') {
                min--;
                sb.append("1");
            }
            else sb.append("0");
        }
        sb.reverse();
        int curBit = Integer.parseInt(sb.toString(), 2);

        dp = new int[20][ 1 << 17];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        if (min < 0) System.out.println(0);
        else {
            int ans = recur(min, curBit);

            if (ans == 1 << 30) System.out.println(-1);
            else System.out.println(ans);
        }

    }
}
