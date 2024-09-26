import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] arr;
    static boolean[][] visited;

    // 아래부터 움직이기 시작
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static boolean dfs(int row, int col, int sr, int sc, int cnt) {
        if (cnt >= 4 && row == sr && col - 1 == sc) {
            return true;
        }

        boolean ret = false;
        int mr, mc;
        for (int dir = 0; dir < 4; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (mr < 0 || mc < 0 || N <= mr || M <= mc
                    || visited[mr][mc] || arr[mr][mc] != arr[sr][sc]) continue;

            visited[mr][mc] = true;
            ret = ret | dfs(mr, mc, sr, sc, cnt + 1);
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new char[N][M];
        visited = new boolean[N][M];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        boolean ret = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                ret = ret | dfs(i, j, i, j, 1);

                for (boolean[] v : visited) {
                    Arrays.fill(v, false);
                }
            }
        }

        if (ret) System.out.println("Yes");
        else System.out.println("No");
    }
}

