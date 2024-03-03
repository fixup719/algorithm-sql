import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] rowCheck = new boolean[9][10];
    static boolean[][] colCheck = new boolean[9][10];
    static boolean[][] squareCheck = new boolean[9][10];
    static int[][] map = new int[9][9];
    static boolean isFinish = false;
    static StringBuilder sb;

    static int square(int row, int col) {
        return (row / 3) * 3 + (col / 3);
    }

    static void recur(int row, int col) {

        if(isFinish) return;

        if (col == 9) {
            col = 0;
            row++;
        }

        if (row == 9) {
            sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
            isFinish = true;
            return;
        }

        if (map[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (rowCheck[row][i] || colCheck[col][i]
                        || squareCheck[square(row, col)][i]) continue;

                rowCheck[row][i] = true;
                colCheck[col][i] = true;
                squareCheck[square(row, col)][i] = true;
                map[row][col] = i;

                recur(row, col + 1);

                rowCheck[row][i] = false;
                colCheck[col][i] = false;
                squareCheck[square(row, col)][i] = false;
                map[row][col] = 0;
            }
            return;
        } else {
            recur(row, col + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        for (int i = 0; i < 9; i++) {
            input = br.readLine();

            for (int j = 0; j < 9; j++) {
                map[i][j] = input.charAt(j) - '0';

                if (map[i][j] != 0) {
                    rowCheck[i][map[i][j]] = true;
                    colCheck[j][map[i][j]] = true;
                    squareCheck[square(i,j)][map[i][j]] = true;
                }
            }
        }

        recur(0, 0);

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}