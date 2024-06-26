import java.io.*;
import java.util.*;

/*
 변을 공유하지 않으며 스티커를 선택해서
 최대 점수 구하기

 스티커는 총 2N개,
 한 줄에 N개씩 스티커가 주어짐

 */

public class Main {
    static int N;
    static int[][] arr;
    static int[][] dp;

    static int recur(int cur, int row) {
        if (cur == N) return 0;

        if (dp[cur][row] != -1) return dp[cur][row];

        int ret = 0;
        if (row == 0) {
            // 이전 열에서 아무 스티커도 선택하지 않은 경우

            // 1행의 스티커 선택
            ret = Math.max(ret, recur(cur + 1, 1) + arr[1][cur]);

            // 2행의 스티커 선택
            ret = Math.max(ret, recur(cur + 1, 2) + arr[2][cur]);

            // 아무 스티커도 선택 X
            ret = Math.max(ret, recur(cur + 1, 0));

        } else if (row == 1) {
            // 이전 열에서 1행의 스티커 선택

            // 2행의 스티커 선택
            ret = Math.max(ret, recur(cur + 1, 2) + arr[2][cur]);

            // 아무 스티커도 선택 X
            ret = Math.max(ret, recur(cur + 1, 0));
        } else {
            // 이전 열에서 2행의 스티커 선택

            // 1행의 스티커 선택
            ret = Math.max(ret, recur(cur + 1, 1) + arr[1][cur]);

            // 아무 스티커도 선택 X
            ret = Math.max(ret, recur(cur + 1, 0));
        }

        dp[cur][row] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[3][N];
            for (int i = 1; i <= 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp = new int[N][3];
            for (int[] d : dp) {
                Arrays.fill(d, -1);
            }

            sb.append(recur(0, 0)).append("\n");
        }

        System.out.println(sb);
    }
}