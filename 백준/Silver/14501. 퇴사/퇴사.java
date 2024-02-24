import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N; // 상담 개수
    static int[][] schedules; // 상담 리스트
    static int[] selected;  // 선택된 스케쥴
    static int answer = 0;  // 최대 이익

    static void recur(int cur, int cnt) {
        if (cur == N + 1) {

            int nextDay = 0;
            int profit = 0;
            boolean check = true;
            for (int i = 0; i < cnt; i++) {
                if (selected[i] < nextDay){
                    check = false;
                    break;
                }

                nextDay = selected[i] + schedules[selected[i]][0];
                if (nextDay - 1 > N){
                    check = false;
                    break;
                }

                profit += schedules[selected[i]][1];
            }

            if(!check) return;

            answer = Math.max(answer, profit);

            return;
        }

        // 스케쥴 선택
        selected[cnt] = cur;
        recur(cur + 1, cnt + 1);
        recur(cur + 1, cnt);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 상담 개수 입력 받기
        N = Integer.parseInt(br.readLine());

        // 상담 리스트 입력 받기
        schedules = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            schedules[i][0] = Integer.parseInt(st.nextToken());
            schedules[i][1] = Integer.parseInt(st.nextToken());
        }
        
        selected = new int[N];
        recur(1, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}