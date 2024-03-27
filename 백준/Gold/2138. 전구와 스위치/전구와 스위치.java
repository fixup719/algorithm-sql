import java.io.*;

public class Main {
    static int N;
    static String before;
    static String after;
    static char[] current;
    static int answer = 1 << 30;

    static boolean check(int cur) {

        for (int i = 0; i < cur; i++) {
            if (current[i] != after.charAt(i)) return false;
        }

        return true;
    }

    static void push(int cur) {
        if (current[cur - 1] == '1') current[cur - 1] = '0';
        else current[cur - 1] = '1';

        if (current[cur] == '1') current[cur] = '0';
        else current[cur] = '1';

        if (cur < N - 1) {
            if (current[cur + 1] == '1') current[cur + 1] = '0';
            else current[cur + 1] = '1';
        }
    }

    static void recur(int cur, int total) {

//        if (!check(cur)) return;

        if (cur == N) {
            if (check(cur)) answer = Math.min(answer, total);
            return;
        }

        if (current[cur - 1] != after.charAt(cur - 1)) {
            // 현재 스위치를 누른다.

            push(cur);
            recur(cur + 1, total + 1);

            // 복원
            push(cur);
        } else {
            recur(cur + 1, total);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        before = br.readLine();
        after = br.readLine();

        current = new char[N];
        for (int i = 0; i < N; i++) {
            current[i] = before.charAt(i);
        }

        // 1번 스위치 안 누르고 진행
        recur(1, 0);

        // 1번 스위치 누르기
        if (current[0] == '0') {
            current[0] = '1';
            current[1] = current[1] == '0' ? '1' : '0';
        } else {
            current[0] = '0';
            current[1] = current[1] == '0' ? '1' : '0';
        }
        recur(1, 1);

        if (answer == 1 << 30) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(answer));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}