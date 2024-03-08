import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int w, h;
    static int[][][][] dp;

    // 1. 백트랙킹 코드
//    static void recur(int right, int top, int prvDir, int change) {
//        prvDir : 이전 방향 / change : 연속 회전 횟수
//
//        if (right > w - 1 || top > h - 1 || change >= 2) return;
//
//        if (right == w - 1 && top == h - 1) cnt++; cnt %= 100000;
//
//        if (prvDir == -1) {
//            recur(right + 1, top, -1, 0);
//            recur(right, top + 1, 1, change + 1);
//        } else if (prvDir == 1) {
//            recur(right + 1, top, -1, change + 1);
//            recur(right, top + 1, 1, 0);
//        } else {
//            recur(right + 1, top, -1, 0);
//            recur(right, top + 1, 1, 0);
//        }
//
//    }

    // 2. 함수로 바꾸기
//    static int recur(int right, int top, int prvDir, int change) {
//
//        if (right > w - 1 || top > h - 1 || change >= 2) return 0;
//
//        if (right == w - 1 && top == h - 1) return 1;
//
//        if (prvDir == -1) {
//            return (recur(right + 1, top, -1, 0) + recur(right, top + 1, 1, change + 1)) % 100000;
//        } else if (prvDir == 1) {
//            return (recur(right + 1, top, -1, change + 1) + recur(right, top + 1, 1, 0)) % 100000;
//        } else {
//            return (recur(right + 1, top, -1, 0) + recur(right, top + 1, 1, 0)) % 100000;
//        }
//    }

    // 3. 메모이제이션 추가
        static long recur(int right, int top, int prvDir, int change) {

            if (right > w - 1 || top > h - 1 || change >= 2) return 0;

            if (right == w - 1 && top == h - 1) return 1;

            if (dp[right][top][prvDir][change] != -1) return dp[right][top][prvDir][change];

            long ret;
            if (prvDir == 1) {
                ret =  (recur(right + 1, top, 1, 0) + recur(right, top + 1, 2, change + 1));
            } else if (prvDir == 2) {
                ret = (recur(right + 1, top, 1, change + 1) + recur(right, top + 1, 2, 0));
            } else {
                ret = (recur(right + 1, top, 1, 0) + recur(right, top + 1, 2, 0));
            }

            dp[right][top][prvDir][change] = (int)(ret % 100000);

            return dp[right][top][prvDir][change];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        dp = new int[w][h][3][3];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                for (int k = 0; k < 3; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        bw.write(String.valueOf(recur(0, 0, 0, 0)));
        bw.flush();
        bw.close();
        br.close();
    }
}