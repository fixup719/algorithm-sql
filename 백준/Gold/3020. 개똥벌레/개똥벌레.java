import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] objects = new int[H + 2];
        int len;
        for (int i = 0; i < N; i++) {
            len = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                // 석순
                objects[1] += 1;
                objects[len + 1] += -1;
            } else {
                // 종유석
                objects[H - len + 1] += 1;
                objects[H + 1] += -1;
            }
        }

        int answer = Integer.MAX_VALUE, cnt = 0;
        for (int i = 1; i <= H; i++) {
            objects[i] += objects[i-1];

            if (answer > objects[i]) {
                cnt = 1;
                answer = objects[i];
            } else if (answer == objects[i]) {
                cnt++;
            }
        }

        bw.write(answer + " " + cnt);
        bw.flush();
        bw.close();
        br.close();
    }
}