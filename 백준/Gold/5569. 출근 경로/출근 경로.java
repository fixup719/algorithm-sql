import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][][] dp = new int[w + 10][h + 10][3][3];

        for (int i = 2; i <= w ; i++) {
            dp[i][1][0][0] = 1;
        }

        for (int i = 2; i <= h; i++) {
            dp[1][i][1][0] = 1;
        }

        for (int i = 2; i <= w; i++) {
            for (int j = 2; j <= h; j++) {
                dp[i][j][0][0] = (dp[i - 1][j][0][0] + dp[i - 1][j][0][1]) % 100000;
                dp[i][j][1][0] = (dp[i][j - 1][1][0] + dp[i][j - 1][1][1]) % 100000;
                dp[i][j][0][1] = (dp[i - 1][j][1][0]) % 100000;
                dp[i][j][1][1] = (dp[i][j - 1][0][0]) % 100000;
            }
        }

        bw.write(String.valueOf((dp[w][h][0][0] + dp[w][h][0][1] + dp[w][h][1][0] + dp[w][h][1][1])% 100000));
        bw.flush();
        bw.close();
        br.close();
    }
}