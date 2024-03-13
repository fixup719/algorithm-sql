import java.io.*;
import java.util.*;

public class Main {
    static String N;
//    static Set<Integer> set = new TreeSet<>();
//    static ArrayList<Integer> list = new ArrayList<>();
    static int[] dp;

    // 진 부분 문자열 추출하기
//    static void findSubString(String str) {
//        list.clear();
//        set.clear();
//        String tmp;
//        for (int s = 0; s < str.length(); s++) {
//            if(str.charAt(s)=='0') continue;
//            for (int e = s + 1; e <= str.length(); e++) {
//                tmp = str.substring(s, e);
//                if (!str.equals(tmp)) {
//                    if (!set.contains(Integer.parseInt(tmp))) {
//                        list.add(Integer.parseInt(tmp));
//                        set.add(Integer.parseInt(tmp));
//                    }
//                }
//            }
//        }
//    }

    static int recur(String num) {
        int numToInt = Integer.parseInt(num);

        if (numToInt < 10) return -1; // 이기게 됨

        if (dp[numToInt] != 1 << 30) {
            return dp[numToInt];
        }

        int ret = 1 << 30, tmp;
        for (int s = 0; s < num.length(); s++) {
            if(num.charAt(s)=='0') continue;
            for (int e = s + 1; e <= num.length(); e++) {
                tmp = Integer.parseInt(num.substring(s, e));
                if (tmp == numToInt) continue;
                if (recur(String.valueOf(numToInt - tmp)) == -1) {
                    ret = Math.min(ret, tmp);
                }
            }
        }


        if (ret == 1<<30) ret = -1;

        dp[numToInt] = ret;

        return dp[numToInt];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = br.readLine();

        dp = new int[1000010];
        Arrays.fill(dp, 1 << 30);

        bw.write(String.valueOf(recur(N)));
        bw.flush();
        bw.close();
        br.close();
    }
}