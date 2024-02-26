import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N; // 이동하려는 채널
    static int M; // 고장난 버튼 개수
    static boolean[] isBroken = new boolean[10]; // 고장난 버튼인지 체크(인덱스 : 버튼 숫자)
    static int[] selected = new int[6]; // 선택한 숫자들을 담을 배열
    static int answer; // 눌러야하는 횟수

    static void recur(int cur, boolean isFront) {
        if (cur == 6) {


            int num = 0; // 숫자를 눌러서 이동할 채널
            // 선택한 숫자를 순회하면서 채널 숫자 만들기(?)
            for (int i = 5, j = 1; i >= 0; i--) {
                num += selected[i] * j;
                j *= 10;
            }

            // 자릿수만큼 버튼을 눌러야하므로 자릿수 세기
            int cnt = (num == 0 ? 1 : 0); // 눌러야하는 숫자 버튼 갯수(0일 경우 while문에 못들어가니까 1로 초기화)
            int tmp = num; // num 임시 변수
            while (tmp != 0) {
                cnt++;
                tmp /= 10;
            }

            // 숫자 이동후 N과의 차이값만큼 더하기
            cnt += Math.abs(N - num);

            answer = Math.min(answer, cnt);

            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (isBroken[i]) {
                if (isFront && i == 0 && cur != 5) {
                    selected[cur] = i;
                } else {
                    continue;
                }
            } else {
                selected[cur] = i;
                isFront = false;
            }
            recur(cur + 1, isFront);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                isBroken[Integer.parseInt(st.nextToken())] = true;
            }
        }


        // answer 값 초기화 : + 또는 - 버튼만 눌러서 이동하는 경우
        answer = Math.abs(100 - N);

        recur(0, true);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}