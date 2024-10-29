import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;
    static boolean[][] visited;
    static int[] delR = {1, -1};
    static int[] delC = {1, -1};
    static int recur(int row, int col) {

        int ret = 0, mr, mc;
        for (int dir = 0; dir < 2; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (mr < 0 || mc < 0 || N <= mr || N <= mc || visited[mr][mc]) continue;

            visited[mr][mc] = true;
            ret = Math.max(ret, recur(mr, mc) + arr[mr][mc]);
            visited[mr][mc] = false;
        }

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int ret;
        for (int tc = 1; tc <= T; tc++) {
            sb.append("Case #").append(tc).append(": ");
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ret = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j]) continue;
                    visited[i][j] = true;
                    ret = Math.max(ret, recur(i, j) + arr[i][j]);
                    visited[i][j] = false;
                }
            }

            sb.append(ret).append("\n");
        }

        System.out.println(sb);
    }
}

