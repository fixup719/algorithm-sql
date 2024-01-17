import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int cnt;
    static int[] ans = new int[2];
    static int[][] locs;

    static int getGcd(int a, int b) {
        int tmp;
        while (b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }

    static void findCase(int depth, int start) {

        if (depth == 2) {

            int x1 = locs[ans[0]][0];
            int x2 = locs[ans[1]][0];
            int y1 = locs[ans[0]][1];
            int y2 = locs[ans[1]][1];

            if (getGcd(Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1))
                    , Math.min(Math.abs(x2 - x1), Math.abs(y2 - y1)))+ 1 == K) {
                cnt++;
            }

            return;
        }

        for (int i = start; i < locs.length; i++) {
            ans[depth] = i;
            findCase(depth+1, i+1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        locs = new int[(N+1)*(M+1)][2];
        for (int i = 0, x = 0, y = 0; i < locs.length; i++) {
            locs[i][0] = (x++) % (N + 1);
            locs[i][1] = y;
            if (i % (N + 1) == N) {
                y++;
            }
        }
//        for (int i = 0; i < locs.length; i++) {
//            System.out.println(locs[i][0]+" "+locs[i][1]);
//        }
//        System.out.println(locs.length);

        findCase(0, 0);
        System.out.println(cnt);

    }
}

// 나한테 왜그래..ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ