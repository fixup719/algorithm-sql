import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static int[][] dpMax;
    static int[][] dpMin;
    static int max;
    static int min = 1 << 30;

    static int recurMax(int row, int col) {

        if (row >= N) return 0;

        if (dpMax[row][col] != -1) return dpMax[row][col];

        int retMax = 0;
        if (col - 1 >= 0) {
            retMax = Math.max(recurMax(row + 1, col - 1) + arr[row][col], retMax);
        }

        if (col + 1 < 3) {
            retMax = Math.max(recurMax(row + 1, col + 1) + arr[row][col], retMax);
        }

        retMax = Math.max(recurMax(row + 1, col) + arr[row][col], retMax);

        dpMax[row][col] = retMax;
        return dpMax[row][col];
    }

    static int recurMin(int row, int col) {

        if (row >= N) return 0;

        if (dpMin[row][col] != -1) return dpMin[row][col];

        int retMin = 1 << 30;
        if (col - 1 >= 0) {
            retMin = Math.min(recurMin(row + 1, col - 1) + arr[row][col], retMin);
        }

        if (col + 1 < 3) {
            retMin = Math.min(recurMin(row + 1, col + 1) + arr[row][col], retMin);
        }

        retMin = Math.min(recurMin(row + 1, col) + arr[row][col], retMin);

        dpMin[row][col] = retMin;
        return dpMin[row][col];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dpMax = new int[N][3];
        dpMin = new int[N][3];

        for (int[] ints : dpMax) {
            Arrays.fill(ints, -1);
        }

        for (int[] ints : dpMin) {
            Arrays.fill(ints, -1);
        }

        for (int i = 0; i < 3; i++) {
            max = Math.max(max, recurMax(0, i));
            min = Math.min(min, recurMin(0, i));
        }

        bw.write(max + " " + min);
        bw.flush();
        bw.close();
        br.close();
    }
}