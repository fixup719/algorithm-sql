import java.io.*;
import java.util.*;

public class Main {
    static int N, M, H;
    static ArrayList<Integer>[] lst;
    static int[][] dp;
    static final int MOD = 10_007;

    static int recur(int cur, int high) {
        if(high > H) return 0;

        if (cur == N) {
            if (high == H) return 1;
            else return 0;
        }

        if (dp[cur][high] != -1) return dp[cur][high];

        int ret = 0;

        for (int i = 0; i < lst[cur].size(); i++) {
            // 현재 블록 쌓기
            ret = (ret + recur(cur + 1, high + lst[cur].get(i)) % MOD) % MOD;
        }
        // 현재 학생은 pass
        ret = (ret + recur(cur + 1, high) % MOD) % MOD;

        dp[cur][high] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        String input;
        String[] c;
        for (int i = 0; i < N; i++) {
            input = br.readLine();

            c = input.split(" ");
            for (int j = 0; j < c.length; j++) {
                lst[i].add(Integer.parseInt(c[j]));
            }
        }

        dp = new int[60][1010];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(0, 0);
        System.out.println(ans);
    }
}