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

        // 경계를 벗어나면 return 1
        if (!check(row,col)) return 1;

        // 이미 방문한 곳을 또 방문하면 return 0 -> 무한루프 방지
        if (visited[row][col]) return 0;

        // 방문 체크
        visited[row][col] = true;

        // 이미 메모되어 있는 경우에는 기록된거 가져오기
        if (dp[row][col] != -1){
            // 방문 체크 해제 -> 복원
            visited[row][col] = false;
            return dp[row][col];
        }

        // 이동
        int mrow = row, mcol = col;
        if (map[row][col] == 'U')  mrow--;
        else if (map[row][col] == 'R') mcol++;
        else if (map[row][col] == 'D') mrow++;
        else mcol--;

        // 재귀 결과값 담기 (1이면 탈출한 거)
        int ret = backtracking(mrow, mcol);

        // 방문 체크 해제 -> 복원
        visited[row][col] = false;

        // 기록
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
                // 출발지마다 돌리기..
                answer += backtracking(i, j);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}