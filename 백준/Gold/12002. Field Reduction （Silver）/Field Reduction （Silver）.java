import java.io.*;
import java.util.*;

public class Main {
    // TODO: 배열을 두 개쓰지 않고 풀 수 있는 법 생각하기 + 중복없이 답는 더 좋은 법도,,
    static int[] selected = new int[3]; // 제거할 좌표
    static int N; // 소 마리수
    static int[][] cowsByX; // X좌표 기준 정렬할 소 좌표
    static int[][] cowsByY; // Y좌표 기준 정렬할 소 좌표
    static ArrayList<int[]> list = new ArrayList<>(); // 제거 대상 좌표들 담을 리스트
    static int answer = Integer.MAX_VALUE; // 넓이

    static void recur(int cur, int cnt) {

        if (cur == list.size()) {

            int x, y, minX = cowsByX[0][0], maxX = cowsByX[N - 1][0], minY = cowsByY[0][1], maxY = cowsByY[N - 1][1];
            int cnt1 = 1, cnt2 = 1, cnt3 = 2, cnt4 = 2;
            for (int i = 0; i < 3; i++) {
                x = list.get(selected[i])[0];
                y = list.get(selected[i])[1];

                if (minX == x) minX = cowsByX[cnt1++][0];
                if (maxX == x){ maxX = cowsByX[N - cnt3][0]; cnt3++;}
                if (minY == y) minY = cowsByY[cnt2++][1];
                if (maxY == y){ maxY = cowsByY[N - cnt4][1]; cnt4++;}
            }

            answer = Math.min(answer, (maxX - minX) * (maxY - minY));

            return;
        }

        if (cnt < 3) {
            selected[cnt] = cur;
            recur(cur + 1, cnt + 1);
        }

        recur(cur + 1, cnt);

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        cowsByX = new int[N][2];
        cowsByY = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cowsByX[i][0] = Integer.parseInt(st.nextToken());
            cowsByY[i][0] = cowsByX[i][0];
            cowsByX[i][1] = Integer.parseInt(st.nextToken());
            cowsByY[i][1] = cowsByX[i][1];
        }

        // x좌표 기준 정렬
        Arrays.sort(cowsByX, (o1, o2) -> {
            return o1[0] - o2[0];
        });

        for (int i = 0; i < 3; i++) {

            boolean same1 = false, same2 = false;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(i)[0] == cowsByX[i][0] && list.get(i)[1] == cowsByX[i][1]) same1 = true;
                if (list.get(i)[0] == cowsByX[N - i - 1][0] && list.get(i)[1] == cowsByX[N - i - 1][1]) same2 = true;
            }

            if (!same1) list.add(new int[]{cowsByX[i][0], cowsByX[i][1]});
            if (!same2) list.add(new int[]{cowsByX[N - i - 1][0], cowsByX[N - i - 1][1]});
        }

        // y좌표 기준 정렬
        Arrays.sort(cowsByY, (o1, o2) -> {
            return o1[1] - o2[1];
        });

        for (int i = 0; i < 3; i++) {

            boolean same1 = false, same2 = false;
            for (int j = 0; j < list.size(); j++) {
                if(list.get(i)[0] == cowsByY[i][0] && list.get(i)[1] == cowsByY[i][1]) same1 = true;
                if(list.get(i)[0] == cowsByY[N - i - 1][0] && list.get(i)[1] == cowsByY[N - i - 1][1]) same2 = true;
            }

            if (!same1) list.add(new int[]{cowsByY[i][0], cowsByY[i][1]});
            if (!same2) list.add(new int[]{cowsByY[N - i - 1][0], cowsByY[N - i - 1][1]});
        }
        
        recur(0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}