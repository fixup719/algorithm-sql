import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[][] paper = new int[10][10]; // 종이
    static int[] colors = {0, 5, 5, 5, 5, 5}; // 색종이 개수 (인덱스가 변의 길이라 생각)
    static boolean[][] visited = new boolean[10][10]; // 방문 체크
    static int answer = Integer.MAX_VALUE; // 붙여야하는 색종이 최소 개수

    // 색종이를 붙일 수 있는지 체크한다.
    static boolean isAble(int row, int col, int s) {
        // row : 행 위치, col : 열 위치, s: 변 길이

        // 색종이를 붙였을 때 경계를 벗어날 경우 붙일 수 없으므로
        if (10 < row + s || 10 < col + s) {
            return false;
        }

        for (int i = row; i < row + s; i++) {
            for (int j = col; j < col + s; j++) {
                if (paper[i][j] == 0 || visited[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    static void recur(int row, int col, int count) {
        // count : 필요한 색종이 개수

        // 이걸 추가해야 시간초과 안뜬다. => count가 answer보다 커지는 경우는 더 볼 필요도 없음
        if(count > answer) return;

        if (col == 10) {
            col = 0;
            row++;
        }

        if (row == 10) {
            answer = Math.min(answer, count);
            return;
        }

        if (paper[row][col] == 1 && !visited[row][col]) {
            for (int i = 5; i >= 1; i--) {
                // 색종이 종류만큼 체크
                if (0 < colors[i] && isAble(row, col, i)) {
                    // 색종이를 붙였다면 색종이 개수 카운트
                    colors[i]--;

                    // 색종이를 붙일 수 있다면 방문체크
                    for (int j = row; j < row + i; j++) {
                        for (int k = col; k < col + i; k++) {
                            visited[j][k] = true;
                        }
                    }

                    recur(row, col + i, count + 1);

                    // 색종이 개수 복원
                    colors[i]++;

                    // 방문 체크 해제 해주기
                    for (int j = row; j < row + i; j++) {
                        for (int k = col; k < col + i; k++) {
                            visited[j][k] = false;
                        }
                    }
                }
            }
        } else {
            recur(row, col + 1, count);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, 0);

        if (answer == Integer.MAX_VALUE) {
            bw.write(String.valueOf(-1));
        } else {
            bw.write(String.valueOf(answer));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}