import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int cnt;
    static int danji;
    static ArrayList<Integer> list = new ArrayList<>();


    static boolean binaryCheck(int row, int col) {
        return 0 <= row && 0 <= col && row < N && col < N;
    }

    static void dfs(int row, int col) {
        cnt++;
        visited[row][col] = true;

        int mrow, mcol;
        for (int dir = 0; dir < 4; dir++) {
            mrow = row + delR[dir];
            mcol = col + delC[dir];

            if (!binaryCheck(mrow,mcol) || visited[mrow][mcol] || map[mrow][mcol] == 0) continue;

            dfs(mrow, mcol);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] || map[i][j] == 0) continue;

                danji++;
                cnt = 0;
                dfs(i, j);
                list.add(cnt);
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        sb.append(danji + "\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i) + "\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}