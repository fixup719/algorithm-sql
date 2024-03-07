import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N; // 집의 수
    static int[][] arr; // 집마다 RGB를 설치했을 때 드는 비용
    static int[][] dp; // 최소 비용 메모이제이션

    static int recur(int cur, int prevColor) {

        int ret = 1 << 30;

        if (cur == N) {
            return 0;
        }

        if (dp[cur][prevColor] != -1) {
            return dp[cur][prevColor];
        }

        for (int i = 1; i <= 3; i++) {

            if (i == prevColor) continue;

            ret = Math.min(ret, recur(cur + 1, i) + arr[cur][i]);
        }

        dp[cur][prevColor] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][4];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            arr[i][3] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][4];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        bw.write(String.valueOf(recur(0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }
}