import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] arr;

    // 하 좌하 우하 우 - 상 우상 좌상 좌
    static int[] delR = {1, 1, 1, 0, -1, -1, -1, 0};
    static int[] delC = {0, -1, 1, 1, 0, 1, -1, -1};
    static int answer = 1 << 30;

    static void recur(int row, int col, int cnt, int isOdd, int max, int dir) {
        int mr, mc;
        mr = row + delR[dir];
        mc = col + delC[dir];

        if (mr < 0 || mc < 0 || 20 <= mr || 20 <= mc) {
            if (cnt == 5) answer = Math.min(answer, max);
            return;
        }
        if (arr[mr][mc] == 0 || (arr[mr][mc] % 2 != isOdd)) {
            if (cnt == 5) answer = Math.min(answer, max);
            return;
        }
        if (arr[mr][mc] != 0 && arr[mr][mc] % 2 == isOdd) recur(mr, mc, cnt + 1, isOdd, Math.max(max, arr[mr][mc]), dir);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[20][20];
        int r, c;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            arr[r][c] = i;
        }

        int mr, mc;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (arr[i][j] == 0) continue;

                for (int dir = 0; dir < 4; dir++) {
                    mr = i + delR[dir + 4];
                    mc = j + delC[dir + 4];

                    if (0 <= mr && 0 <= mc && mr < 20 && mc < 20 && arr[mr][mc] != 0 && (arr[mr][mc] % 2 == arr[i][j] % 2)) continue;

                    recur(i, j, 1, arr[i][j] % 2, arr[i][j], dir);
                }
            }
        }

        if (answer == 1 << 30) System.out.println(-1);
        else System.out.println(answer);
    }
}

