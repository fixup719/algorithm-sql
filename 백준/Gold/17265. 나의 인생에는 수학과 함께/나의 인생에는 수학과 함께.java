import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] delR = {1, 0};
    static int[] delC = {0, 1};

    static int getMax(int row, int col, int cal, int prvNum) {
        if (row == N - 1 && col == N - 1) return prvNum;

        int ret = Integer.MIN_VALUE, mrow, mcol;
        for (int dir = 0; dir < 2; dir++) {
            mrow = row + delR[dir];
            mcol = col + delC[dir];

            if (N <= mrow || N <= mcol) continue;

            if (cal == -1) {
                ret = Math.max(ret, getMax(mrow, mcol, 0, prvNum + map[mrow][mcol]));
            } else if (cal == -2) {
                ret = Math.max(ret, getMax(mrow, mcol, 0, prvNum - map[mrow][mcol]));
            } else if (cal == -3) {
                ret = Math.max(ret, getMax(mrow, mcol, 0, prvNum * map[mrow][mcol]));
            } else {
                ret = Math.max(ret, getMax(mrow, mcol, map[mrow][mcol], prvNum));
            }

        }

        return ret;
    }

    static int getMin(int row, int col, int cal, int prvNum) {
        if (row == N - 1 && col == N - 1) return prvNum;

        int ret = 1 << 30, mrow, mcol;
        for (int dir = 0; dir < 2; dir++) {
            mrow = row + delR[dir];
            mcol = col + delC[dir];

            if (N <= mrow || N <= mcol) continue;

            if (cal == -1) {
                ret = Math.min(ret, getMin(mrow, mcol, 0, prvNum + map[mrow][mcol]));
            } else if (cal == -2) {
                ret = Math.min(ret, getMin(mrow, mcol, 0, prvNum - map[mrow][mcol]));
            } else if (cal == -3) {
                ret = Math.min(ret, getMin(mrow, mcol, 0, prvNum * map[mrow][mcol]));
            } else {
                ret = Math.min(ret, getMin(mrow, mcol, map[mrow][mcol], prvNum));
            }
        }

        return ret;
    }
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        String input;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                input = st.nextToken();
                if (input.equals("+")) map[i][j] = -1;
                else if (input.equals("-")) map[i][j] = -2;
                else if (input.equals("*")) map[i][j] = -3;
                else map[i][j] = Integer.parseInt(input);
            }
        }

        int max = getMax(0, 0, 0, map[0][0]);
        int min = getMin(0, 0, 0, map[0][0]);

        System.out.println(max + " " + min);
    }
}