import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] boards;
    static int cnt;
    static int answer = Integer.MAX_VALUE;

    static int onPaint(int row, int col, char color) {
        cnt = 0;
        if (row % 2 == 0) {
            for (int i = row; i < row + 8; i++) {
                for (int j = col; j < col + 8; j++) {
                    if ((i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1)) {
                        if (boards[i][j] != color) cnt++;
                    } else {
                        if (boards[i][j] == color) cnt++;
                    }
                }
            }
        } else {
            for (int i = row; i < row + 8; i++) {
                for (int j = col; j < col + 8; j++) {
                    if ((i % 2 == 0 && j % 2 == 1) || (i % 2 == 1 && j % 2 == 0)) {
                        if (boards[i][j] != color) cnt++;
                    } else {
                        if (boards[i][j] == color) cnt++;
                    }
                }
            }
        }

        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boards = new char[N][M];
        String str;
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            for (int j = 0; j < M; j++) {
                boards[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                // 시작 포인트 설정 (i,j)

                answer = Math.min(answer, Math.min(onPaint(i, j, 'B'), onPaint(i, j, 'W')));
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}