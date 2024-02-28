import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr = new int[12][12];
    static int[] selected = new int[12];
    static boolean[] visited = new boolean[12];
    static int answer = Integer.MAX_VALUE;

    static void recur(int cur, int total, int groupNo) {

        if (cur == 12) {
            int sum = 0;
            for (int i = 0; i < 11; i++) {
                sum += arr[selected[i]][selected[i + 1]];
            }

            answer = Math.min(answer, sum);

            return;
        }

        for (int i = groupNo * 2 - 2; i < groupNo * 2; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            selected[cur] = i;

            if (total == 2) {
                recur(cur + 1, 1, groupNo + 1);
            } else {
                recur(cur + 1, total + 1, groupNo);
            }

            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 12; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 12; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 1, 1);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}