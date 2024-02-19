import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] colors;
    static int[] gomduri;
    static int size;
    static int[] selected;
    static int answer = Integer.MAX_VALUE;

    static void recur(int cur, int start) {
        if (cur == size) {

            int R = 0, G = 0, B = 0;
            for (int i = 0; i < size; i++) {
                R += colors[selected[i]][0];
                G += colors[selected[i]][1];
                B += colors[selected[i]][2];
            }

            answer = Math.min(answer, Math.abs(gomduri[0] - R / size)
                    + Math.abs(gomduri[1] - G / size) + Math.abs(gomduri[2] - B / size));

            return;
        }

        for (int i = start; i < N; i++) {
            selected[cur] = i;
            recur(cur + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        colors = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            colors[i][0] = Integer.parseInt(st.nextToken());
            colors[i][1] = Integer.parseInt(st.nextToken());
            colors[i][2] = Integer.parseInt(st.nextToken());
        }

        gomduri = new int[3];
        st = new StringTokenizer(br.readLine());
        gomduri[0] = Integer.parseInt(st.nextToken());
        gomduri[1] = Integer.parseInt(st.nextToken());
        gomduri[2] = Integer.parseInt(st.nextToken());

        selected = new int[N];
        for (int i = 2; i <= 7; i++) {
            if(i > N) break;

            size = i;

            recur(0, 0);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}