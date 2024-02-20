import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] selected;
    static boolean isEnd;

    static boolean check(int cur) {

        if(cur <= 1) return true;
        int cnt;
        for (int i = 1; i <= cur / 2; i++) {
            // 부분 수열 크기 결정

            cnt = 0;
            for (int k = 0; k < i; k++) {
                // 부분수열 같은지 체크
                if (selected[cur - i * 2 + k] == selected[cur - i * 2 + i + k]) {
                    cnt++;
                }
            }

            if(cnt == i) return false;

        }

        return true;
    }

    static void recur(int cur) {
        if(!check(cur)) return;

        if (cur == N) {
            isEnd = true;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if(isEnd) return;
            selected[cur] = i;
            recur(cur + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        selected = new int[N];
        recur(0);

        StringBuilder sb = new StringBuilder();
        for (int num : selected) {
            sb.append(num);
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}