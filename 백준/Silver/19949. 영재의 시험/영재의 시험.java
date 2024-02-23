import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int[] arr; // 문제의 정답
    static int[] selected; // 선택한 정답
    static int answer; // 정답 개수

    static void recur(int cur, int correct) {
        // cur : 지금까지 선택한 위치
        // correct : 맞은 개수
        if (cur == 10) {

            // 맞은 개수가 5개 미만이면 pass
            if(correct < 5) return;

            // 3개 연속인 답이 있는지 체크
            boolean check = true;
            for (int i = 0; i <= 7; i++) {
                if (selected[i] == selected[i + 1] && selected[i] == selected[i + 2]) {
                    check = false;
                    break;
                }
            }

            // 조건 충족시 카운팅
            if(check) answer++;

            return;
        }

        for (int i = 1; i <= 5; i++) {
            selected[cur] = i;
            if (arr[cur] == i) {
                // 정답이 갖다면 correct + 1
                recur(cur + 1, correct + 1);
            } else {
                // 갖지 않다면 correct
                recur(cur + 1, correct);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[10];
        for (int i = 0; i < 10; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        selected = new int[10];
        recur(0,0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}