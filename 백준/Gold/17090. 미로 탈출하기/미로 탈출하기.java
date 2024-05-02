import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dp;


    static boolean check(int row, int col) {
        return 1 <= row && 1 <= col && row <= N && col <= M;
    }
    static int backtracking(int row, int col) {

        if (!check(row,col)) return 1;

        if (visited[row][col]) return 0;

        visited[row][col] = true;

        if (dp[row][col] != -1){
            visited[row][col] = false;
            return dp[row][col];
        }


        int mrow = row, mcol = col;
        if (map[row][col] == 'U')  mrow--;
        else if (map[row][col] == 'R') mcol++;
        else if (map[row][col] == 'D') mrow++;
        else mcol--;

        int ret = 0;

        ret += backtracking(mrow, mcol);

        visited[row][col] = false;

        dp[row][col] = ret;
        return dp[row][col];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N + 1][M + 1];
        String input;
        for (int i = 1; i <= N; i++) {
            input = br.readLine();

            for (int j = 1; j <= M; j++) {
                map[i][j] = input.charAt(j - 1);
            }
        }

        visited = new boolean[N + 1][M + 1];

        dp = new int[N + 10][M + 10];
        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        int answer = 0;
        int tmp;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                answer += backtracking(i, j);

//                for (boolean[] booleans : visited) {
//                    Arrays.fill(booleans, false);
//                }
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}