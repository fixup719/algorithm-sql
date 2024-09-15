import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static char[][] arr;
    static boolean[][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int wolf, sheep;

    static void dfs(int cr, int cc) {
        visited[cr][cc] = true;
        if (arr[cr][cc] == 'v') wolf++;
        else if (arr[cr][cc] == 'k') sheep++;

        int mr, mc;
        for (int dir = 0; dir < 4; dir++) {
            mr = cr + delR[dir];
            mc = cc + delC[dir];

            if (mr < 0 || mc < 0 || R <= mr || C <= mc
                    || arr[mr][mc] == '#' || visited[mr][mc]) continue;

            dfs(mr, mc);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];
        visited = new boolean[R][C];

        String input;
        for (int i = 0; i < R; i++) {
            input = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        int wfSum = 0, spSum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j] || arr[i][j] == '#') continue;
                dfs(i, j);

                if (wolf < sheep) {
                    spSum += sheep;
                } else {
                    wfSum += wolf;
                }

                wolf = 0;
                sheep = 0;
            }
        }

        System.out.println(spSum + " " + wfSum);
    }
}

