import java.io.*;

public class Main {
    static int N;
    static int[] selected;
    static int answer;

    static boolean check(int cur) {
        for (int i = 0; i < cur; i++) {
            if (selected[cur] == selected[i]
                    || Math.abs(cur - i) == Math.abs(selected[cur] - selected[i])) {
                return false;
            }
        }
        return true;
    }

    static void recur(int cur) {
        if (cur == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            selected[cur] = i;
            if (check(cur)) {
                recur(cur + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        selected = new int[N];
        recur(0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}