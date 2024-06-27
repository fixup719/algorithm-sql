import java.io.*;
import java.util.*;

/*
[문제요약]
8가지 등급의 카드 존재
카드 팩 형태로만 구매 가능 -> 1개, 2개, ..., N개가 들어있는 카드팩(N가지)
최소한의 돈을 지불해서 카드 N개를 구매하려함
카드 i가 포함된 카드팩 가격은 pi원

카드 팩의 가격이 주어졌을 때,
N개의 카드를 구매하기 위해 민규가 지불해야 하는 금액의 최솟값 구하기(딱 N개)

N : 1 ~ 1000
P1...PN (1 <= Pi <= 1만)

카드 N개를 갖기 위해 지불해야할 금액 최솟값?
 */

public class Main {
    static int N;
    static int[] packCost;
    static int[][] dp;

    static int recur(int cur, int sum) {
        if (sum > N) return 1 << 30;

        if (cur == N + 1) {
            if (sum == N) return 0;
            else return 1 << 30;
        }

        if (dp[cur][sum] != -1) return dp[cur][sum];

        int ret = 1 << 30;

        // 현재 카드팩 선택 X
        ret = Math.min(ret, recur(cur + 1, sum));

        // 현재 카드팩 선택후 다음 카드 팩 넘기기
        ret = Math.min(ret, recur(cur + 1, sum + cur) + packCost[cur]);

        // 현재 카드팩 선택후 유지
        ret = Math.min(ret, recur(cur, sum + cur) + packCost[cur]);

        dp[cur][sum] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        packCost = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            packCost[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[1010][1010];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }

        System.out.println(recur(1, 0));
    }
}