import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] arr;
    static int sr, sc, er, ec, idx;
    static boolean[][][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};


    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[M][N][1 << 6];
        q.offer(new int[]{sr, sc, 0});
        visited[sr][sc][0] = true;

        int cr, cc, cs, mr, mc, bit, size, time = 0;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.peek()[1];
                cs = q.poll()[2];

                if (cr == er && cc == ec && cs == (1 << (idx + 1)) - 2) {
                    return time;
                }

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || M <= mr || N <= mc) continue;
                    if (visited[mr][mc][cs]) continue;
                    
                    if (arr[mr][mc] == 0) {
                        q.offer(new int[]{mr, mc, cs});
                        visited[mr][mc][cs] = true;
                    } else if (arr[mr][mc] > 0) {
                        bit = cs | (1 << arr[mr][mc]);
                        q.offer(new int[]{mr, mc, bit});
                        visited[mr][mc][bit] = true;
                    }
                }
            }
            time++;
        }

        return time;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M][N];
        String input;
        char ch;
        for (int i = 0; i < M; i++) {
            input = br.readLine();
            for (int j = 0; j < N; j++) {
                ch = input.charAt(j);

                if (ch == 'S') {
                    arr[i][j] = 0;
                    sr = i;
                    sc = j;
                } else if (ch == 'E') {
                    arr[i][j] = 0;
                    er = i;
                    ec = j;
                } else if (ch == '#') {
                    arr[i][j] = -1;
                } else if (ch == '.') {
                    arr[i][j] = 0;
                } else if (ch == 'X') {
                    arr[i][j] = ++idx;
                }
            }
        }
        System.out.println(bfs());
    }
}
