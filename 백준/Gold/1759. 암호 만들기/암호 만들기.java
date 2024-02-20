import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static String[] chars;
    static String[] selected;
    static StringBuilder sb = new StringBuilder();

    static boolean check() {

        int cnt = 0; // 모음 개수
        for (int i = 0; i < L; i++) {
            if (selected[i].equals("a") || selected[i].equals("e") || selected[i].equals("i")
                    || selected[i].equals("o") || selected[i].equals("u")) {
                cnt++;
            }
        }

        if(cnt < 1) return false;
        if (L - cnt < 2) return false;

        return true;
    }

    static void recur(int cur, int start) {
        if (cur == L) {
            if (check()) {
                for (String aChar : selected) {
                    sb.append(aChar);
                }
                sb.append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            selected[cur] = chars[i];
            recur(cur + 1, i + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        chars = new String[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken();
        }
        Arrays.sort(chars);

        selected = new String[L];
        recur(0, 0);

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}