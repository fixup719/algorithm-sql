import java.io.*;
import java.util.*;

public class Main {
    static int T, H, W;
    static int[][] arr;
    static boolean[][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};

    static void dfs(int row, int col) {
        int mr, mc;
        for (int dir = 0; dir < 4; dir++) {
            mr = row + delR[dir];
            mc = col + delC[dir];

            if (mr < 0 || mc < 0 || H <= mr || W <= mc || arr[mr][mc] == '.' || visited[mr][mc]) continue;

            visited[mr][mc] = true;
            dfs(mr, mc);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        String input;
        int cnt;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            arr = new int[H][W];
            for (int i = 0; i < H; i++) {
                input = br.readLine();
                for (int j = 0; j < W; j++) {
                    arr[i][j] = input.charAt(j);
                }
            }

            visited = new boolean[H][W];

            cnt = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (visited[i][j] || arr[i][j] == '.') continue;
                    dfs(i, j);
                    cnt++;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}

