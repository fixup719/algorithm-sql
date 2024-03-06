import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] prefix = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + Integer.parseInt(br.readLine());
        }


        int s = 1, e = 2, dist1, dist2;
        int answer = 0;
        while (e <= N) {
            // 시계 방향 거리
            dist1 = prefix[e - 1] - prefix[s - 1];

            // 반시계 방향 거리
            dist2 = prefix[N] - dist1;

            answer = Math.max(answer, Math.min(dist1, dist2));

            if(dist1 > dist2) s++;
            else e++;
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}