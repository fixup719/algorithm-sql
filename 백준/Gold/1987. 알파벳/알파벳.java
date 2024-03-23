import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] map;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static boolean[][] visited;
    static Set<Character> set = new LinkedHashSet<>();
    static int ans;

    static boolean dfs(int row, int col, int visitCnt) {

        // 모든 글자가 다른 보드인 경우를 생각해서 기저조건이 하나 더 필요 함
        if (set.contains(map[row][col]) || visitCnt == R * C) {
            if(visitCnt == R * C ) ans = Math.max(ans, set.size() + 1);
            else ans = Math.max(ans, set.size());
            return false;
        }

        visited[row][col] = true;
        set.add(map[row][col]);

        int mrow, mcol;
        boolean ret;
        for (int dir = 0; dir < 4; dir++) {
            mrow = row + delR[dir];
            mcol = col + delC[dir];

            if (mrow < 0 || mcol < 0 || R <= mrow || C <= mcol || visited[mrow][mcol]) continue;

            ret = dfs(mrow, mcol, visitCnt + 1);

            if (ret){
                set.remove(map[mrow][mcol]);
                visited[mrow][mcol] = false;
            }

        }
        return true;
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
        dfs(0, 0, 1);

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}