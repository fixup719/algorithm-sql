import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[] delC = {-1, 0, 1};
    static int[][] dp1;
    static int[][] dp2;

    // 다음 줄로 내려간다면
    // 바로 아래수 또는 바로 아래수와 붙어있는 수로만 이동 가능

    static int[] getMax (int row, int prvCol) {
        if (row == N + 1) return new int[]{0, 0};

        if (dp1[row][prvCol] != -1 && dp2[row][prvCol] != -1) {
            return new int[]{dp1[row][prvCol], dp2[row][prvCol]};
        }

        int ret1 = 0, ret2 = 1 << 30, mc;
        for (int dir = 0; dir < 3; dir++) {
            mc = prvCol + delC[dir];

            if (mc < 0 || 3 <= mc) continue;

            ret1 = Math.max(ret1, getMax(row + 1, mc)[0] + arr[row][mc]);
            ret2 = Math.min(ret2, getMax(row + 1, mc)[1] + arr[row][mc]);
        }

        dp1[row][prvCol] = ret1;
        dp2[row][prvCol] = ret2;
        return new int[] {ret1, ret2};
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp1 = new int[N + 10][3];
        for (int[] d : dp1) {
            Arrays.fill(d, -1);
        }
        dp2 = new int[N + 10][3];
        for (int[] d : dp2) {
            Arrays.fill(d, -1);
        }

        int[] ans = getMax(0, 1);

        System.out.println(ans[0] + " " + ans[1]);


        br.close();
    }
}