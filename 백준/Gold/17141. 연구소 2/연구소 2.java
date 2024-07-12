import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<int[]> virusIdx = new ArrayList<>();
    static Queue<int[]> q = new LinkedList<>();
    static boolean[][] visited;
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};
    static int empty;
    static int min = 1 << 30;

    static int spreadVirus() {

        int cr, cc, mr, mc, size, time = 0, remain = empty - M;
        while (!q.isEmpty()) {
            size = q.size();

            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.poll()[1];

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || N <= mr || N <= mc) continue;
                    if (visited[mr][mc] || map[mr][mc] == 1) continue;

                    visited[mr][mc] = true;
                    q.offer(new int[]{mr, mc});
                    remain--;
                }
            }
            time++;
        }

        if (!q.isEmpty()) q.clear();

        for (boolean[] v : visited) {
            Arrays.fill(v, false);
        }

        if (remain <= 0) return time - 1;
        else return 1 << 30;
    }

    static void recur(int cur, int start) {
        if (cur == M) {
            for (int i = 0, len = virusIdx.size(); i < len; i++) {
                if (virusIdx.get(i)[2] == 1) {
                    // 바이러스를 놓은 곳만 큐에 담기
                    visited[virusIdx.get(i)[0]][virusIdx.get(i)[1]] = true;
                    q.add(new int[]{virusIdx.get(i)[0], virusIdx.get(i)[1]});
                }
            }

            int ans = spreadVirus();

            min = Math.min(ans, min);
            return;
        }

        // 바이러스 M개 놓는 경우 선택하기
        for (int i = start, len = virusIdx.size(); i < len; i++) {
            virusIdx.get(i)[2] = 1;
            recur(cur + 1, i + 1);
            virusIdx.get(i)[2] = 0;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) virusIdx.add(new int[]{i, j, 0});
                if (map[i][j] != 1) empty++;
            }
        }

        recur(0, 0);

        if (min != 1 << 30) System.out.println(min);
        else System.out.println(-1);
    }
}
