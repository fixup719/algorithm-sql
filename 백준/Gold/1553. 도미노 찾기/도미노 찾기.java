import java.io.*;

public class Main {
    static int[][] arr = new int[8][7]; // 주어진 격자판
    static boolean[][] dominos = new boolean[7][7]; // 도미노 종류별 방문 체크
    static boolean[][] visited = new boolean[8][7]; // 격자판 방문 체크
    static int[] delR = {-1, 1, 0, 0}; // 행 : 상하좌우
    static int[] delC = {0, 0, -1, 1}; // 열 : 상하좌우
    static int answer; // 경우의 수

    static void recur(int row, int col) {

        if (col == 7) {
            row++;
            col = 0;
        }

        if (row == 8) {
            answer++;
            return;
        }

        // 이미 체크한 칸이라면
        if (visited[row][col]) {
            recur(row, col + 1);
        } else {
            // 도미노는 회전이 가능하므로 상하좌우 탐색한다.
            int num1 = arr[row][col], num2; // 적힌 숫자
            for (int dir = 0; dir < 4; dir++) {
                int mrow = row + delR[dir];
                int mcol = col + delC[dir];

                // row, col 위치가 경계를 벗어나거나 이미 방문한 격자칸이라면 pass
                if (mrow < 0 || mcol < 0 || 8 <= mrow || 7 <= mcol || visited[mrow][mcol]) {
                    continue;
                }

                num2 = arr[mrow][mcol];

                // 이미 사용한 도미노라면 pass
                if(dominos[num1][num2] && dominos[num2][num1]) continue;

                // 도미노를 사용한다.
                dominos[num1][num2] = true;
                dominos[num2][num1] = true;
                // 격자판 방문 체크한다.
                visited[mrow][mcol] = true;
                visited[row][col] = true;

                recur(row, col + 1);

                // 도미노 사용 여부 해제
                dominos[num1][num2] = false;
                dominos[num2][num1] = false;
                // 격자판 방문 체크 해제
                visited[mrow][mcol] = false;
                visited[row][col] = false;
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str;
        for (int i = 0; i < 8; i++) {
            str = br.readLine();
            for (int j = 0; j < 7; j++) {
                arr[i][j] = str.charAt(j) - 48;
            }
        }

        recur(0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}