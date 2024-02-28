import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] map = new int[9][9];
    static boolean[][] rowCheck = new boolean[9][10];
    static boolean[][] colCheck = new boolean[9][10];
    static boolean[][] squareCheck = new boolean[9][10];
    static StringBuilder sb = new StringBuilder();

    static int square(int row, int col) {
        return (row / 3) * 3 + (col / 3);
    }

    static void recur(int row, int col) {

        if (col == 9) {
            col = 0;
            row++;
        }

        if (row == 9) {

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j] + " ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        if (map[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (!rowCheck[row][i] && !colCheck[col][i] && !squareCheck[square(row, col)][i]) {
                    map[row][col] = i;
                    rowCheck[row][i] = colCheck[col][i] = squareCheck[square(row,col)][i] = true;

                    recur(row, col + 1);
                    map[row][col] = 0;
                    rowCheck[row][i] = colCheck[col][i] = squareCheck[square(row,col)][i] = false;
                }
            }
            return;
        }

        recur(row, col + 1);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    rowCheck[i][map[i][j]] = true;
                    colCheck[j][map[i][j]] = true;
                    squareCheck[square(i,j)][map[i][j]] = true;
                }
            }
        }

        recur(0,0);

        bw.flush();
        bw.close();
        br.close();
    }
}