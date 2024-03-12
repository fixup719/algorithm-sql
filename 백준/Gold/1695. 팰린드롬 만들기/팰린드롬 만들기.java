import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] nums;
//    static int answer = 1 << 30;

    static int[][] dp;

    // 1. 백트랙킹
//    static void recur(int s, int e, int cnt) {
//
//        if (s == e) {
//            answer = Math.min(answer, cnt);
//            return;
//        }
//
//        if (nums[s] == nums[e]) {
//            recur(s + 1, e - 1, cnt);
//        } else {
//            recur(s, e - 1, cnt + 1);   // arr[s]뒤에 nums[e]값 추가
//            recur(s + 1, e, cnt + 1);   // arr[e]앞에 nums[s]값 추가
//        }
//
//    }

    // 2. 함수 + 메모이제이션 적용하기
    static int recur(int s, int e) {

        if (s >= e) return 0;

        if (dp[s][e] != -1) return dp[s][e];

        int ret = 1 << 30;
        if (nums[s] == nums[e]) {
            ret = Math.min(ret, recur(s + 1, e - 1));
        } else {
            ret = Math.min(ret, recur(s, e - 1) + 1);   // arr[s]뒤에 nums[e]값 추가
            ret = Math.min(ret, recur(s + 1, e) + 1);   // arr[e]앞에 nums[s]값 추가
        }

        dp[s][e] = ret;
        return dp[s][e];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

//        recur(0, N - 1, 0);

        dp = new int[5010][5010];
        for (int i = 0; i < 5010; i++) {
            Arrays.fill(dp[i], -1);
        }

        bw.write(String.valueOf(recur(0, N - 1)));
        bw.flush();
        bw.close();
        br.close();
    }
}