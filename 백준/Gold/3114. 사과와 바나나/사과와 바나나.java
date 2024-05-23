import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] banana;
    static int[][] apple;

    // 아래, 오른쪽, 오른쪽 대각선 아래
    static int[] delR = {1, 0, 1};
    static int[] delC = {0, 1, 1};

    static int[][] dp;

    static int recur(int row, int col) {

        if (row == R && col == C) return 0;

        if (dp[row][col] != -1) return dp[row][col];

        int mr, mc, ret = 0;
        for (int dir = 0; dir < 3; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (R < mr || C < mc) continue;

            if (dir == 0) {
                // 아래 이동 시, 즉 mr가 1칸 아래로 옮기면 bSum += banana[row][C] - banana[row][col]
                ret = Math.max(ret, recur(mr, mc) + banana[row][C] - banana[row][col]);
            } else if (dir == 1) {
                // 오른쪽 이동 시, 즉 mc가 1칸 뒤로 옮기면 aSum += apple[R][col] - apple[row][col]
                ret = Math.max(ret, recur(mr, mc) + apple[R][col] - apple[row][col]);
            } else {
                // 오른쪽 아래 대각선 이동 시, aSum += apple[R][col] - apple[row][col], bSum += banana[row][C] - banana[row][col]
                ret = Math.max(ret, recur(mr, mc) + banana[row][C] - banana[row][col] + apple[R][col] - apple[row][col]);
            }
        }

        dp[row][col] = ret;
        return ret;
    }

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        banana = new int[R + 1][C + 1];
        apple = new int[R + 1][C + 1];
        
        String str; char tree; int cnt;
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                str = st.nextToken();

                tree = str.charAt(0);
                cnt = Integer.parseInt(str.substring(1));

                if (tree == 'B') {
                    banana[i][j] = cnt;
                } else {
                    apple[i][j] = cnt;
                }
            }
        }

        // 2차원 누적합 배열 만들기
        // 바나나 -> 행별로 가로 누적
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                banana[r][c] += banana[r][c - 1];
            }
        }
        // 사과 -> 열별로 세로 누적
        for (int c = 1; c <= C; c++) {
            for (int r = 1; r <= R; r++) {
                apple[r][c] += apple[r - 1][c];
            }
        }

        dp = new int[R + 10][C + 10];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(recur(1, 1));
        
        br.close();
    }
}