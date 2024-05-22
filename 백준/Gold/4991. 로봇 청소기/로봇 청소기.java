import java.io.*;
import java.util.*;

public class Main {
    static int W, H;
    static char[][] arr;
    static int robotR, robotC;
    static int trashCnt;
    static boolean[][][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int[] delR = {-1, 1, 0, 0};
    static int[] delC = {0, 0, -1, 1};


    static int bfs() {
        q.offer(new int[]{robotR, robotC, 0});
        visited[robotR][robotC][0] = true;

        int cr, cc, mr, mc, trash, size, move = 0;
        while (!q.isEmpty()) {

            size = q.size();

            for (int s = 0; s < size; s++) {
                cr = q.peek()[0];
                cc = q.peek()[1];
                trash = q.poll()[2];

                if (trash == (1 << trashCnt) - 1) {
                    q.clear();
                    return move;
                }

                for (int dir = 0; dir < 4; dir++) {
                    mr = cr + delR[dir];
                    mc = cc + delC[dir];

                    if (mr < 0 || mc < 0 || H <= mr || W <= mc || arr[mr][mc] == 'x') continue;

                    if (arr[mr][mc] != '.') {
                        // 쓰레기 만난 경우

                        // 방문 체크
                        if (visited[mr][mc][trash | 1 << (arr[mr][mc] - '0')]) continue;

                        visited[mr][mc][trash | 1 << (arr[mr][mc] - '0')] = true;
                        q.offer(new int[]{mr, mc, trash | 1 << (arr[mr][mc] - '0')});
                    } else {
                        // 빈 공간인 경우

                        //방문 체크
                        if (visited[mr][mc][trash]) continue;

                        visited[mr][mc][trash] = true;
                        q.offer(new int[]{mr, mc, trash});
                    }
                }
            }

            move++;
        }

        return -1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        String input;
        while (true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if (W == 0 && H == 0) break;

            arr = new char[H][W];
            trashCnt = 0;
            for (int i = 0; i < H; i++) {
                input = br.readLine();
                for (int j = 0; j < W; j++) {
                    arr[i][j] = input.charAt(j);

                    if (arr[i][j] == '*'){
                        arr[i][j] = Integer.toString(trashCnt++).charAt(0);
                    } else if (arr[i][j] == 'o') {
                        robotR = i;
                        robotC = j;
                        arr[i][j] = '.';
                    }
                }
            }

            visited = new boolean[H][W][1 << (trashCnt + 1)];
            sb.append(bfs()).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}