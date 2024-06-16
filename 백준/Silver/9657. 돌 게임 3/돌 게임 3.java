import java.io.*;
import java.util.*;

/*
    돌 N개가 놓여져 있고
    2명이 번갈아서 1개 or 3개 or 4개 씩 가져갈 수 있음
    마지막 돌을 가져가는 사람이 이김

    이때 각자 완벽히 게임을 했을 때 이기는 사람 궇기
    상근이가 먼저 시작
    상근이가 이기면 SK
    창영이가 이기면 CY
 */

public class Main {
    static int[] getStone = {1, 3, 4};
    static int[][] dp;

    static int recur(int remain, int order){
        if (remain == 0){
            if (order == 0) {
                // 상근이 차례에 이미 돌이 남은게 없다면 창영이가 이김
                return 1;
            } else {
                // 상근이가 이김
                return 0;
            }
        }

        if (dp[remain][order] != -1) return dp[remain][order];

        int ret;
        if (order == 0) {
            ret = 1 << 30;
            for (int i = 0; i < 3; i++) {
                if (remain < getStone[i]) continue;
                ret = Math.min(ret, recur(remain - getStone[i], 1));
            }
        } else {
            ret = 0;
            for (int i = 0; i < 3; i++) {
                if (remain < getStone[i]) continue;
                ret = Math.max(ret, recur(remain - getStone[i], 0));
            }
        }

        dp[remain][order] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        dp = new int[N + 10][2];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        int ans = recur(N, 0);

        if (ans == 0) System.out.println("SK");
        else System.out.println("CY");

        br.close();
    }
}