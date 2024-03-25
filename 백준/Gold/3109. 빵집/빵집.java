import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    // 우상 우 우하
    static int[] delR = {-1, 0, 1};
    static int[] delC = {1, 1, 1};
    static boolean[][] visited;
    static int cnt;


    static boolean check(int row, int col) {
        return 0 <= row && 0 <= col && row < R && col < C
                && !visited[row][col] && map[row][col] == '.';
    }
    static boolean dfs(int row, int col) {

        if (col == C - 1) return true;

        int mrow, mcol;
        boolean ret;
        for (int i = 0; i < 3; i++) {
            mrow = row + delR[i];
            mcol = col + delC[i];

            if (!check(mrow, mcol)) continue;

            visited[mrow][mcol] = true;
            ret = dfs(mrow, mcol);

            if (ret) return ret;
            
//            visited[mrow][mcol] = false;
        }

        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        String input;
        for (int i = 0; i < R; i++) {
            input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        visited = new boolean[R][C];


        for (int i = 0; i < R; i++) {
            if (dfs(i, 0)) cnt++;
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}