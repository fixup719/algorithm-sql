import java.io.*;
import java.util.*;

public class Main {
    /*

       N개의 표적이 준비
       1번부터 N번까지 표적이 줄줄이 매달려 있음 (맨위1, 맨아래N)
       두 플레이어가 번갈아서 아래 두 동작 중 한 가지 선택
       1. 표적 하나 선택하고 맞히기
       2. 기권

       만약 맞추면 맞춘 사람의 점수에 합산되며, 파괴된 표적 밑에 달린 표적은 바닥으로 떨어짐
        떨어졌지만 맞추지 않은 표적은 상대방 점수에 합산
       만약 기권할 경우 모든 표적 점수가 상대방 점수에 합산되고 게임 종료
       두 플레이어는 모두 자신의 점수를 최대화해서 선택

       이때 선공이 가져갈 점수 출력

       게임이론 -> 각자 최선의 선택 (선공 기준하면, 후공 차례에서는 반환값이 가장 작은 걸 선택)
     */
    static int N;
    static long[] scoreAcc;
    static long[][] dp;

    static long recur(int remain, int order) {

        if (remain == 1) return 0;

        if (dp[remain][order] != -1) return dp[remain][order];

        long ret;
        if (order == 0) {
            // 선공 공격 차례 1 2 3 4 5
            ret = -200_000_000_000_000_000L;
            for (int i = 1; i < remain; i++) {
                // 맞추기
                ret = Math.max(ret, recur(i, 1) + scoreAcc[i] - scoreAcc[i - 1]);
            }
            // 기권
            ret = Math.max(recur(1, 1), ret);
        } else {
            // 후공 공격 차례
            ret = 200_000_000_000_000_000L;
            for (int i = 1; i < remain; i++) {
                // 맞추기
                ret = Math.min(ret, recur(i, 0) + scoreAcc[remain - 1] - scoreAcc[i]);
            }
            // 기권
            ret = Math.min(recur(1, 0) + scoreAcc[remain - 1], ret);
        }

        dp[remain][order] = ret;
        return ret;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        scoreAcc = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            scoreAcc[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= N; i++) {
            scoreAcc[i] += scoreAcc[i - 1];
        }

        dp = new long[200010][2];
        for (long[] d1 : dp) {
            Arrays.fill(d1, -1);
        }

        System.out.println(recur(N + 1, 0));

        br.close();
    }
}