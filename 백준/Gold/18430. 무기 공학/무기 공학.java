import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] delR = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    static int[][] delC = {{-1, 0}, {0, 1}, {-1, 0}, {0, 1}};
    static int ans;

    static void recur(int row, int col, int sum) {

        if (col == M) {
            col = 0;
            row++;
        }

        if (row == N) {
            ans = Math.max(ans, sum);
            return;
        }

        if (visited[row][col]) {
            recur(row, col + 1, sum);
        } else {
            // 퍼즐 안 만들기
            recur(row, col + 1, sum);
            // 퍼즐 만들기
            int pr1, pc1, pr2, pc2;
            for (int i = 0; i < 4; i++) {
                pr1 = row + delR[i][0];
                pc1 = col + delC[i][0];
                pr2 = row + delR[i][1];
                pc2 = col + delC[i][1];

                if (pr1 < 0 || pc1 < 0 || N <= pr1 || M <= pc1
                        || pr2 < 0 || pc2 < 0 || N <= pr2 || M <= pc2
                        || visited[pr1][pc1] || visited[pr2][pc2]) continue;

                visited[row][col] = true;
                visited[pr1][pc1] = true;
                visited[pr2][pc2] = true;

                recur(row, col + 1, sum + arr[row][col] * 2 + arr[pr1][pc1] + arr[pr2][pc2]);

                visited[row][col] = false;
                visited[pr1][pc1] = false;
                visited[pr2][pc2] = false;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, 0);

        System.out.println(ans);
    }
}