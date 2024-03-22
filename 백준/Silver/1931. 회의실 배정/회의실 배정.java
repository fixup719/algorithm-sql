import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 끝나는 시간 기준 오름차순(같다면 시작시간 기준 오름차순)
        Arrays.sort(arr, (o1,o2)->{
            if (o1[1] == o2[1]) return o1[0] - o2[0];
            else return o1[1] - o2[1];});

        /*
        * 현재 회의 끝나는 시간이랑 다음 회의 시작 시간 비교
        * => 시작 가능하다면 해당 회의로 이동 하고 카운트
        * */
        int cnt = 1, curIdx = 0;
        for (int i = 1; i < N; i++) {
            if (arr[curIdx][1] <= arr[i][0]) {
                curIdx = i;
                cnt++;
            }
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}