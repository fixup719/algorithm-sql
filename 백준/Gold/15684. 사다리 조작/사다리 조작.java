import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H; // N : 세로선 개수, M : 가로선 개수, H : 세로선마다 가로선 놓을 수 있는 위치 개수
    static int[][] map; // 사다리 맵
    static int answer = Integer.MAX_VALUE; // 설치해야할 가로선 최소 개수
    static boolean check; // 연속됐는지 체크하기위한 변수

    // 경계 체크
    static boolean boundaryCheck(int row, int col) {
        return 0 <= row && row <= H && 0 <= col && col < N * 2;
    }

    // 사다리 타기 진행
    static boolean onProgress() {

        int row, tmpCol;
        for (int col = 1; col < 2 * N; col += 2) {
            // 좌우 탐색부터 할 것!
            row = 1;
            tmpCol = col;
            while (row <= H) {
                if (boundaryCheck(row, tmpCol + 1) && map[row][tmpCol + 1] == 1) {
                    // 만약 우측 값이 1이라면(가로선이 있다면)
                    row++;
                    tmpCol += 2;
                } else if (boundaryCheck(row, tmpCol - 1) && map[row][tmpCol - 1] == 1) {
                    // 만약 좌측 값이 1이라면(가로선이 있다면)
                    row++;
                    tmpCol -= 2;
                } else {
                    // 만약 좌우 가로선이 없다면 아래로
                    row++;
                }
            }
            if(col != tmpCol) return  false;
        }

        return true;
    }
    static void recur(int row,int col, int total) {

        // 설치한 가로선 개수가 3개보다 많을 경우는 제외 시켜야함(가지치기 용)
        if (total > 3) return;

        if (col >= N * 2) {
            col = 2;
            row++;
        }

        if (row == H + 1) {

            // i번째에서 출발한게 i번쨰로 도착하는 경우 개수 갱신
            if(onProgress()){ answer = Math.min(answer, total);}

            return;
        }

        check = true;
        // 이미 가로선이 설치된 경우만 빼고 보기
        if (map[row][col] == 0) {

            // 좌 또는 우에 이미 가로선이 설치되면 안 됨 (연속되면 안되니까)
            if ((boundaryCheck(row, col - 2) && map[row][col - 2] == 1)) {
                check = false;
            }
            if ((boundaryCheck(row, col + 2) && map[row][col + 2] == 1)) {
                check = false;
            }

            if (check) {
                // 가로선 설치하기
                map[row][col] = 1;
                recur(row, col + 2, total + 1);

                // 설치한 거 복원
                map[row][col] = 0;
            }
        }

        // 가로선 설치 안 함
        recur(row, col + 2, total);


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 세로선에 1표시 -> 사다리 기둥이니까
        map = new int[H + 1][2 * N];
        for (int i = 1; i <= H; i++) {
            for (int j = 1; j < 2 * N; j += 2) {
                map[i][j] = 1;
            }
        }

        // 이미 설치된 가로선 설치
        int row, col;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            map[row][col * 2] = 1;
        }

        recur(1, 2, 0);

        bw.write(String.valueOf(answer == Integer.MAX_VALUE ? -1 : answer));
        bw.flush();
        bw.close();
        br.close();
    }
}