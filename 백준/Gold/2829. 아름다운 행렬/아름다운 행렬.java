import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] aPrefix;
    static int[][] bPrefix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine().trim());
        aPrefix = new int[N + 2][N + 2];
        bPrefix = new int[N + 2][N + 2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 1; j <= N; j++) {
                aPrefix[i][j] = Integer.parseInt(st.nextToken());
                bPrefix[i][j] = aPrefix[i][j];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                aPrefix[i][j] = aPrefix[i - 1][j - 1] + aPrefix[i][j];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                bPrefix[i][j] = bPrefix[i - 1][j + 1] + bPrefix[i][j];
            }
        }

        int max = Integer.MIN_VALUE, sumA, sumB;
        for (int size = 1; size <= N; size++) {
            for (int row = size; row <= N; row++) {
                for (int col = size; col <= N; col++) {
                    // size 2, row 3, col 3
                    sumA = aPrefix[row][col] - aPrefix[row - size][col - size];
                    sumB = bPrefix[row][col - size + 1] - bPrefix[row - size][col + 1];
                    max = Math.max(max, sumA - sumB);
                }
            }
        }

        System.out.println(max);
    }
}

