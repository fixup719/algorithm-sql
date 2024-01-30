import java.io.*;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 개수 구간합 구하기
        int[][][] cnt = new int[N + 1][N + 1][11];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= 10; k++) {
                    cnt[i][j][k] = cnt[i-1][j][k] + cnt[i][j-1][k] - cnt[i-1][j-1][k];
                }
                cnt[i][j][arr[i][j]]++;
            }
        }




        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int x1, y1, x2, y2, ans;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            ans = 0;
            for (int i = 1; i <= 10; i++) {
                if (cnt[x2][y2][i] > 0) {
                    if (cnt[x2][y2][i] - cnt[x1 - 1][y2][i] - cnt[x2][y1 - 1][i] + cnt[x1 - 1][y1 - 1][i] > 0) {
                        ans++;
                    }
                }
            }

            sb.append(ans+"\n");

        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}