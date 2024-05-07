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
            // 임도연이 3명이하인 경우만 가능하므로
            if (yCnt < 4) {
                // 인접한 좌석이라면 answer증가
                if (isNear()) answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            selected[cur] = i;

            // 임도연이라면 yCnt를 하나 증가시켜주기
            if (arr[i / 5][i % 5] == 'Y') {
                comb(cur + 1, i + 1, yCnt + 1);
            } else {
                comb(cur + 1, i + 1, yCnt);
            }
        }

    }

    static boolean isNear() {
        int cnt = 1;

        // 선택한 좌석인지 체크하기 위해 set에 담음
        for (int i = 0; i < 7; i++) {
            set.add(selected[i]);
        }

        // 좌석 하나 담고 시작하고
        q.offer(selected[0]);
        visited[selected[0]] = true;

        int cur, crow, ccol, mrow, mcol;
        while (!q.isEmpty()) {
            cur = q.poll();

            // 1차원 인덱스 2차원 인덱스로 바꾸기
            crow = cur / 5;
            ccol = cur % 5;

            // 인접한 곳으로만 이동한다.
            for (int dir = 0; dir < 4; dir++) {
                mrow = crow + delR[dir];
                mcol = ccol + delC[dir];

                // 경계 및 방문 체크
                if (mrow < 0 || mcol < 0 || 5 <= mrow || 5 <= mcol || visited[mrow * 5 + mcol]) continue;

                // 선택한 좌석인지 체크
                if (!set.contains(mrow * 5 + mcol)) continue;

                // 인접하면서 선택된 좌석이라면..
                visited[mrow * 5 + mcol] = true;
                q.offer(mrow * 5 + mcol);
                // 인접 개수 증가
                cnt++;
            }
        }

        // 초기화
        q.clear();
        set.clear();
        Arrays.fill(visited, false);

        // 인접한 개수 cnt가 7개면 return true
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