import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Point> chickens = new ArrayList<>();
    static ArrayList<Point> houses = new ArrayList<>();
    static int chickenCnt, houseCnt;
    static int[] selected;
    static int answer = Integer.MAX_VALUE;

    static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static void recur(int cur, int start) {
        if (cur == M) {

            int sum = 0, dist, hr, hc, kr, kc;
            for (int i = 0; i < houseCnt; i++) {
                dist = 1000;
                hr = houses.get(i).row;
                hc = houses.get(i).col;
                for (int j = 0; j < M; j++) {
                    kr = chickens.get(selected[j]).row;
                    kc = chickens.get(selected[j]).col;
                    dist = Math.min(dist, Math.abs(hr - kr) + Math.abs(hc - kc));
                }
                sum += dist;
            }

            answer = Math.min(answer, sum);

            return;
        }

        for (int i = start; i < chickenCnt; i++) {
            selected[cur] = i;
            recur(cur + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int input;
            for (int j = 0; j < N; j++) {
                input = Integer.parseInt(st.nextToken());

                if (input == 2) {
                    chickens.add(new Point(i, j));
                } else if (input == 1) {
                    houses.add(new Point(i, j));
                }
            }
        }

        chickenCnt = chickens.size();
        houseCnt = houses.size();
        selected = new int[M];

        recur(0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}