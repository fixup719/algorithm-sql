import java.io.*;
import java.util.*;

public class Main {
    static char[][] arr = new char[5][5];
    static int[] selected = new int[7];
    static Set<Integer> set = new TreeSet<>();
    static Queue<Integer> q = new LinkedList<>();
    static boolean[] visited = new boolean[25];
    static int[] delR = {1, 0, -1 ,0};
    static int[] delC = {0, 1, 0 , -1};
    static int answer = 0;

    // 자리 선택하기 -> 중복이 있으면 X -> 따라서 조합
    static void comb(int cur, int start, int yCnt) {

        if (cur == 7) {
            if (yCnt < 4) {
                // 인접한 좌석인지 체크하기
                if (isNear()) answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            selected[cur] = i;

            if (arr[i / 5][i % 5] == 'Y') {
                comb(cur + 1, i + 1, yCnt + 1);
            } else {
                comb(cur + 1, i + 1, yCnt);
            }
        }

    }

    static boolean isNear() {
        int cnt = 1;

        for (int i = 0; i < 7; i++) {
            set.add(selected[i]);
        }

        q.offer(selected[0]);
        visited[selected[0]] = true;

        int cur, crow, ccol, mrow, mcol, size;
        while (!q.isEmpty()) {
            cur = q.poll();

            crow = cur / 5;
            ccol = cur % 5;

            for (int dir = 0; dir < 4; dir++) {
                mrow = crow + delR[dir];
                mcol = ccol + delC[dir];


                if (mrow < 0 || mcol < 0 || 5 <= mrow || 5 <= mcol || visited[mrow * 5 + mcol]) continue;

                if (!set.contains(mrow * 5 + mcol)) continue;

                visited[mrow * 5 + mcol] = true;
                q.offer(mrow * 5 + mcol);
                cnt++;
            }
        }

        q.clear();
        set.clear();
        Arrays.fill(visited, false);
        if (cnt == 7) return true;
        else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        for (int i = 0; i < 5; i++) {
            input = br.readLine();

            for (int j = 0; j < 5; j++) {
                arr[i][j] = input.charAt(j);
            }
        }

        // 2차원을 1차원으로 펼치기 -> row * 5 + col -> 몫이 row값, 나머지가 col값
        // 1차원 인덱스를 2차원으로 바꾸기 row : (1차원 인덱스 값)/5 , col : (1차원 인덱스 값)%5
        comb(0, 0, 0);

        System.out.println(answer);

        br.close();
    }
}