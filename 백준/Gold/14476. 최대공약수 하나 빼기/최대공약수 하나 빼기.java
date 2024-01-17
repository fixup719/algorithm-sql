import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static long getGcd(long a, long b) {
        // a > b 일 것
        long tmp;
        while (b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] nums = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

//        Arrays.sort(nums);

        long[] diff = new long[N-1];
        for (int i = 0; i < N-1; i++) {
            diff[i] = Math.abs(nums[i + 1] - nums[i]);
        }

        long[] LtoR = new long[N-1];
        long[] RtoL = new long[N-1];

        long gcd = diff[0];
        LtoR[0] = gcd;
        for (int i = 0; i < N - 1; i++) {
            gcd = getGcd(Math.max(gcd, diff[i]), Math.min(gcd, diff[i]));
            LtoR[i] = gcd;
        }

        gcd = diff[N-2];
        RtoL[N-2] = gcd;
        for (int i = N-2; i >= 0; i--) {
            gcd = getGcd(Math.max(gcd, diff[i]), Math.min(gcd, diff[i]));
            RtoL[i] = gcd;
        }

        long ansGcd = -1, ansK = 0, tmpGcd, newDiff;
        for (int k = 0; k < N; k++) {
            if (k == 0) {
                tmpGcd = RtoL[k + 1];
            } else if (k == N - 1) {
                tmpGcd = LtoR[k - 2];
            } else {
                newDiff = diff[k - 1] + diff[k];
                if (k - 2 < 0) {
                    tmpGcd = getGcd(Math.max(newDiff, RtoL[k + 1]), Math.min(newDiff, RtoL[k + 1]));
                } else if (N - 2 < k + 1) {
                    tmpGcd = getGcd(Math.max(newDiff, LtoR[k - 2]), Math.min(newDiff, LtoR[k - 2]));
                } else {
                    tmpGcd = getGcd(Math.max(LtoR[k - 2], RtoL[k + 1]), Math.min(LtoR[k - 2], RtoL[k + 1]));
                    tmpGcd = getGcd(Math.max(newDiff, tmpGcd), Math.min(newDiff, tmpGcd));
                }
            }

//            System.out.println(nums[k] + " " + tmpGcd);

            if (nums[k] % tmpGcd != 0) {
                if (ansGcd <= tmpGcd) {
                    ansK = nums[k];
                    ansGcd = tmpGcd;
                }
            }
        }

        if (ansGcd != -1) {
            System.out.println(ansGcd + " " + ansK);
        } else {
            System.out.println(ansGcd);
        }


    }
}