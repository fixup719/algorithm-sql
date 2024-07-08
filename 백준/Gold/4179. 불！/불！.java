import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] map;
    static final int FIRE = 1;
    static final int EMPTY = 0;
    static final int WALL = 2;
    static Queue<int[]> fire = new LinkedList<>();
    static Queue<int[]> jihoon = new LinkedList<>();
    static boolean[][] visitedFire;
    static boolean[][] visitedJihoon;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int ans = 0;

    static void spreadFire() {
        int cr, cc, mr, mc, size;
        while (!fire.isEmpty()) {
            size = fire.size();

            for (int s = 0; s < size; s++) {
                cr = fire.peek()[0];
                cc = fire.poll()[1];

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || R <= mr || C <= mc
                            || visitedFire[mr][mc] || map[mr][mc] == WALL) continue;

                    map[mr][mc] = FIRE;
                    visitedFire[mr][mc] = true;
                    fire.offer(new int[]{mr, mc});
                }
            }
            return;
        }
    }

    static int moveJihoon() {
        int cr, cc, mr, mc, size;
        while (!jihoon.isEmpty()) {
            size = jihoon.size();

            for (int s = 0; s < size; s++) {
                cr = jihoon.peek()[0];
                cc = jihoon.poll()[1];

                if (map[cr][cc] == FIRE) continue;

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    // 탈출 성공
                    if (mr < 0 || mc < 0 || R <= mr || C <= mc) return 1;

                    if (visitedJihoon[mr][mc] || map[mr][mc] == WALL || map[mr][mc] == FIRE) continue;

                    jihoon.offer(new int[]{mr, mc});
                    visitedJihoon[mr][mc] = true;
                }
            }

            return 0;
        }

        return 0;
    }



    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        visitedFire = new boolean[R][C];
        visitedJihoon = new boolean[R][C];
        String input; char c;
        for (int i = 0; i < R; i++) {
            input = br.readLine();
            for (int j = 0; j < C; j++) {
                c = input.charAt(j);
                if (c == '#') map[i][j] = WALL;
                else if (c == '.') map[i][j] = EMPTY;
                else if (c == 'J') {
                    map[i][j] = EMPTY;
                    jihoon.offer(new int[]{i, j});
                } else {
                    map[i][j] = FIRE;
                    visitedFire[i][j] = true;
                    fire.offer(new int[]{i, j});
                }
            }
        }

        int minute = 1;
        String ans = "";
        while (true) {
            // 지훈이 이동
            if (moveJihoon() == 1) break;

            if (jihoon.isEmpty()){
                ans = "IMPOSSIBLE";
                break;
            }

            // 불퍼뜨리기
            spreadFire();

            minute++;
        }

        if (ans.equals("IMPOSSIBLE")) System.out.println(ans);
        else System.out.println(minute);

    }
}
