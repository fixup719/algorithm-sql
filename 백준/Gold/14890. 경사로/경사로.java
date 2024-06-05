import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] arr;
    static boolean[][] rowCheck;
    static boolean[][] colCheck;

    /*
       1. 높은 곳과 높이 차는 1일 것
       2. 낮은 칸에 경사로를 놓을 것
       3. 경사로를 놓는 L개의 연속된 칸의 높이는 같을 것

       [접근]
       1. 높이차이가 발생하는 곳에 경사로를 놓을 것
          abs(arr[i][j] - arr[i][j+1]) > 1 -> 불가능 return 0
          abs(arr[i][j] - arr[i][j+1]) == 0 -> pass
          abs(arr[i][j] - arr[i][j+1]) == 1 -> 경사로 놓기
       2. (1번 충족 가정) 낮은 곳에 경사로 놓기 + 3. L개의 연속된 칸에 놓기(높이 같아야 함)
          len 변수 -> 높이가 같은 연속된 칸 카운트

          if 낮은칸 -> 높은칸 만난 경우
             len변수 그대로 사용
             if (len < L) -> 불가능 return 0
             else
                arr[i][j] ~ arr[i][j-L+1] 까지 경사로 놓을 수 있는지 체크
                    rowcheck가 false 여부 체크
                가능하면 rowCheck 배열(또는 colCheck)에 같은 범위 true 체크
             경사로 놓기가 끝나면 -> arr[i][j+1]부터 다시 카운트 시작
          if 높은칸 -> 낮은칸 만난 경우
             len 변수 초기화
             arr[i][j+1]~arr[i][j+L] 까지 높이가 균일한지 체크
             + rowCheck false 여부 체크
             가능하면 rowCheck 배열(또는 colCheck)에 같은 범위 true 체크


     */

    static int colRoad(int col) {
        int len = 1, diff;
        for (int row = 0; row < N - 1; row++) {
            diff = arr[row][col] - arr[row + 1][col];

            if (diff == 0) {
                len++;
            } else if (Math.abs(diff) > 1) {
                return 0;
            } else {
                if (diff == -1) {
                    // 낮 -> 높
                    if (len < L) return 0;

                    // 이미 경사로 있는지 체크
                    for (int i = row; i >= row - L + 1; i--) {
                        if (colCheck[i][col]) return 0;
                    }

                    // 경사로 놓기
                    for (int i = row; i >= row - L + 1; i--) {
                        colCheck[i][col] = true;
                    }

                    len = 1;

                } else if (diff == 1) {
                    // 높 -> 낮
                    len = 1;

                    // 낮은 곳 높이 균일한지 체크
                    for (int i = row + 1; i < row + L; i++) {
                        if (i + 1 >= N) return 0;
                        if (arr[i][col] != arr[i + 1][col]) return 0;
                    }

                    // 이미 경사로 있는지 체크
                    for (int i = row + 1; i <= row + L; i++) {
                        if (colCheck[i][col]) return 0;
                    }

                    // 경사로 놓기
                    for (int i = row + 1; i <= row + L; i++) {
                        colCheck[i][col] = true;
                    }
                }
            }
        }

        return 1;
    }
    static int rowRoad(int row) {
        int len = 1, diff;
        for (int col = 0; col < N - 1; col++) {
            diff = arr[row][col] - arr[row][col + 1];

            if (diff == 0) {
                len++;
            } else if (Math.abs(diff) > 1) {
                return 0;
            } else {
                if (diff == -1) {
                    // 낮 -> 높
                    if (len < L) return 0;

                    // 이미 경사로 있는지 체크
                    for (int i = col; i >= col - L + 1; i--) {
                        if (rowCheck[row][i]) return 0;
                    }

                    // 경사로 놓기
                    for (int i = col; i >= col - L + 1; i--) {
                        rowCheck[row][i] = true;
                    }

                    len = 1;

                } else if (diff == 1) {
                    // 높 -> 낮
                    len = 1;

                    // 낮은 곳 높이 균일한지 체크
                    for (int i = col + 1; i < col + L; i++) {
                        if (i + 1 >= N) return 0;
                        if (arr[row][i] != arr[row][i + 1]) return 0;
                    }

                    // 이미 경사로 있는지 체크
                    for (int i = col + 1; i <= col + L; i++) {
                        if (rowCheck[row][i]) return 0;
                    }

                    // 경사로 놓기
                    for (int i = col + 1; i <= col + L; i++) {
                        rowCheck[row][i] = true;
                    }
                }
            }
        }

        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rowCheck = new boolean[N][N];
        colCheck = new boolean[N][N];

        int ans = 0;
        for (int row = 0; row < N; row++) {
            ans += rowRoad(row);
        }

        for (int col = 0; col < N; col++) {
            ans += colRoad(col);
        }

        System.out.println(ans);

        br.close();
    }
}