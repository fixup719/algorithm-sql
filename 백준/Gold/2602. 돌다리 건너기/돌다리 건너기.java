import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static String str;
    static String[] road;
    //    static int cnt;
    static int[][][] dp;

    // 탑다운으로 풀어보기...
    static int recur(int cur, int idx, int num) {
        // num : 지나갈 다리 번호
        // idx : 지나가야할 문자 인덱스

        if (idx >= str.length()) {
            return 1;
        }

        if (dp[cur][idx][num] != -1) return dp[cur][idx][num];


        int ret = 0;
        for (int i = cur; i < road[0].length(); i++) {
            if (str.charAt(idx) == road[num].charAt(i)) {
                ret += recur(i + 1, idx + 1, (num + 1) % 2);
            }
        }

        dp[cur][idx][num] = ret;
        return dp[cur][idx][num];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        /*
         * 돌다리 건너는 규칙(두 돌다리 길이는 같음)
         * 1) 적힌 문자열에 적힌 순서대로 지나갈 것
         * 2) 다리 번걸아서 지나갈 것
         * 3) 오른쪽 한 칸 이상 전진
         * */

        str = br.readLine();

        road = new String[2];
        road[0] = br.readLine();
        road[1] = br.readLine();

        dp = new int[road[0].length() + 10][str.length() + 10][2];
        for (int i = 0; i < road[0].length() + 10; i++) {
            for (int j = 0; j < str.length() + 10; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        bw.write(String.valueOf(recur(0, 0, 0) + recur(0, 0,  1)));
        bw.flush();
        bw.close();
        br.close();
    }
}