//55 분

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map = new char[510][510];
    static int[] delR = {-1, 0, 1, 0};
    static int[] delC = {0, 1, 0, -1};
    static char[] dir = {'U', 'R', 'D', 'L'};

    /*
        N*M크기의 항성계가 있다.
        시그널은 블랙홀(C)를 만나거나 항성계범위를 벗어날때까지 계속 전파된다.
        만약, 행성(/, \)을 만나면 아래와 같이 반사 된다.
            위 오 아 왼
            \ : 위->왼, 왼->위, 오->아, 아->오 (0~3, 1~2) =>
            / : 오->위, 왼->아, 아->왼, 위->오 (2~3, 0~1) => %2가 0이면 +1, 1이면 -1
        인접한 칸으로 한 칸 이동하는데 1초가 걸린다.
        어느 방향으로 시그널을 보내야 이동 시간이 최대가 되는지 구하여라

        입력
        N과 M (1~500)
        다음 줄부터 맵이 주어짐(.는 빈칸)
        마지막 줄에는 탐사선 위치 PR, PC가 주어짐 (PR:1~N, PC:1~M)

        출력
        시그널을 보내는 방향을 출력(D,L,R,U)
        만약 여러 가지 존재한다면 순서 중 앞서는 거 출력
        둘째줄에는 가장 긴 시간을 출력한다.
        -> 만약 무한히 전파될 수 있다면 Voyager 출력
    * */

    static int move(int PR, int PC, int CD) {
        int time = 1;

        int cr = PR, cc = PC, mr, mc;
        while (true) {
            mr = cr + delR[CD];
            mc = cc + delC[CD];

            if (mr < 0 || mc < 0 || N <= mr || M <= mc || map[mr][mc] == 'C') break;

            if (time == N * M * 2) {
                // 이미 방문한 곳 또 방문하면 -> 무한히 전파
                time = -1;
                break;
            }

            cr = mr;
            cc = mc;

            if (map[mr][mc] == '.') {
                time++;
                continue;
            } else if (map[mr][mc] == '/') {
                CD = CD % 2 == 0 ? CD + 1 : CD - 1;
            } else {
                CD = 3 - CD;
            }

            time++;
        }

        return time;
    }
    public static int[] solution(int PR, int PC){
        int[] answer = {0,-1};
        int ret;

        for (int i = 0; i < 4; i++) {
            // 현재 방향 i인덱스를 매개변수로 넘김
            ret = move(PR, PC, i);

            if (ret == -1) {
                answer[0] = ret;
                answer[1] = i;
                break;
            } else if (ret > answer[0]) {
                answer[0] = ret;
                answer[1] = i;
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        String input;
        for (int i = 0; i < N; i++) {
            input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int PR = Integer.parseInt(st.nextToken()) - 1;
        int PC = Integer.parseInt(st.nextToken()) - 1;

        int[] ans = solution(PR, PC);

        System.out.println(dir[ans[1]]);
        if (ans[0] == -1) System.out.println("Voyager");
        else System.out.println(ans[0]);

        br.close();
    }
}