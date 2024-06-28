import java.io.*;
import java.util.*;

/*
[문제요약]
스타트링크가 입주한 사무실은 방 번호 직접 정할 수 있음
이떄, 1층 문방구에서 파는 숫자 구매해야함
필요한 금액은 M원

각 숫자 i의 가격은 Pi고 숫자는 0부터 N-1까지 존재
같은 숫자 여러개 구매 가능,
방 번호는 0을 제외하고 0으로 시작 불가

N이 주어지고 다음 줄에
P0... PN-1이 주어짐
마지막줄에는 M

N 은 1~10
Pi는 1~50
M은 1~50

첫째 줄에 최대 M원을 사용해서 만들 수 있는 가장 큰 방 번호를 출력(가능한 입력만 주어짐)

최대 50자 가능함
 */

public class Main {
    static int N, M;
    static int[] price;
    static String[][][] dp;
    static StringBuilder sb;

    static String recur(int cur, int sum, int len, int[] useCnt) {

        if (cur == -1) {
            int cnt;
            sb = new StringBuilder();
            for (int i = N - 1; i >= 0; i--) {
                cnt = useCnt[i];
                if (cnt > 0) {
                    if (i == 0 && sb.isEmpty()) {
                        cnt = 1;
                    }
                    while (cnt-- > 0) {
                        sb.append(i);
                    }
                }
            }

            return sb.toString();
        }

        if (!dp[cur][sum][len].equals("")) return dp[cur][sum][len];

        String ret = "";
        String retStr = "";
        if (sum + price[cur] <= M) {
            // 현재 숫자 선택후 그대로 유지
            int[] copy = useCnt.clone();
            copy[cur]++;
            retStr = recur(cur, sum + price[cur], len + 1, copy);
            if (ret.length() < retStr.length()) {
                ret = retStr;
            }

            // 현재 숫자 선택하고 다음 숫자로 넘어가기
            retStr = recur(cur - 1, sum + price[cur], len + 1, copy);
            if (ret.length() < retStr.length()) {
                ret = retStr;
            }
        }

        // 현재 숫자 선택 X
        retStr = recur(cur - 1, sum, len, useCnt);
        if (ret.length() < retStr.length()){
            ret = retStr;
        }

        dp[cur][sum][len] = ret;
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        price = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        dp = new String[20][60][60];
        for (String[][] d : dp) {
            for (String[] d2 : d) {
                Arrays.fill(d2, "");
            }
        }

        System.out.println(recur(N - 1, 0, 0, new int[10]));

    }
}
