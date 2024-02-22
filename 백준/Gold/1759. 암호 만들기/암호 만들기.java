import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] chars;
    static char[] selected;
    static int[] value = new int[2];
    static StringBuilder sb = new StringBuilder();

    static void check(char x) {
        if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
            value[0] = 1;
            value[1] = 0;
        } else {
            value[0] = 0;
            value[1] = 1;
        }
    }
    static void recur(int cur, int cnt, int v, int c) {
        if (cnt == L) {
            if (v < 1 || c < 2) {
                return;
            }

            for (int i = 0; i < L; i++) {
                sb.append(selected[i]);
            }
            sb.append("\n");

            return;
        }

        if (cur == C) return;

        selected[cnt] = chars[cur];
        check(chars[cur]);
        recur(cur + 1, cnt + 1, v + value[0], c + value[1]);

        selected[cnt] = 0;
        recur(cur + 1, cnt, v, c);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        chars = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(chars);

        selected = new char[L];

        recur(0, 0, 0, 0);

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}