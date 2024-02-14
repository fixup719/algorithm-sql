import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken()) + 500000;
            arr[i][1] = Integer.parseInt(st.nextToken()) + 500000;
        }

        // x좌표 변화 기록
        int[] prefixX = new int[1000010];
        // y좌표 변화 기록
        int[] prefixY = new int[1000010];

        int x1, x2, y1, y2;
        for (int i = 0; i < N; i++) {
            // 첫 좌표 ~ 마지막좌표 잇기
            x1 = arr[i][0];
            y1 = arr[i][1];
            if (i == N - 1) {
                x2 = arr[0][0];
                y2 = arr[0][1];
            } else {
                x2 = arr[i + 1][0];
                y2 = arr[i + 1][1];
            }
            if (x1 != x2) {
                prefixX[Math.min(x1, x2)] += 1;
                prefixX[Math.max(x1, x2)] += -1;
            } else {
                prefixY[Math.min(y1, y2)] += 1;
                prefixY[Math.max(y1, y2)] += -1;
            }
        }

        int h = 0, v = 0;
        for (int i = 1; i < 1000010 ; i++) {
            prefixX[i] += prefixX[i - 1];
            prefixY[i] += prefixY[i - 1];

            h = Math.max(h, prefixX[i]);
            v = Math.max(v, prefixY[i]);
        }

        bw.write(String.valueOf(Math.max(h, v)));
        bw.flush();
        bw.close();
        br.close();
    }
}