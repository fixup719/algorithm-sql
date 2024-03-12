import java.io.*;
import java.util.*;

public class Main {
    static int[] takeOut;

    static int[][] marbles;
    static int[][] dp;

    static int recur(int box1, int box2) {
        // A => who :true / B => who : false
        if (dp[box1][box2] != -1) return dp[box1][box2];

        int ret = 2;
        for (int i = 0; i < 3; i++) {
            if (box1 - takeOut[i] >= 0 && recur(box1 - takeOut[i], box2) == 2) ret = 1;
        }
        for (int i = 0; i < 3; i++) {
            if (box2 - takeOut[i] >= 0 && recur(box1, box2 - takeOut[i]) == 2) ret = 1;
        }
        dp[box1][box2] = ret;
        return dp[box1][box2];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        takeOut = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            takeOut[i] = Integer.parseInt(st.nextToken());
        }

        marbles = new int[5][2];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            marbles[i][0] = Integer.parseInt(st.nextToken());
            marbles[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[510][510];
        for (int i = 0; i < 510; i++) {
            Arrays.fill(dp[i], -1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            if (recur(marbles[i][0], marbles[i][1]) == 1) sb.append('A' + "\n");
            else sb.append('B' + "\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}