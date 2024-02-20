import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;
    static char[] cals;
    static boolean[] visited;
    static int[] selected;
    static int max = -1000000000, min = 1000000000;

    static void recur(int cur) {
        if (cur == N - 1) {
            char sign;
            int num = arr[0];
            for (int i = 0; i < N - 1; i++) {
                sign = cals[selected[i]];
                if (sign == '+') {
                    num += arr[i + 1];
                } else if (sign == '-') {
                    num -= arr[i + 1];
                } else if (sign == '*') {
                    num *= arr[i + 1];
                } else {
                    num /= arr[i + 1];
                }
            }

            max = Math.max(num, max);
            min = Math.min(num, min);
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            selected[cur] = i;
            recur(cur + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int cnt;
        char ch;
        cals = new char[N - 1];
        for (int i = 0, j = 0; i < 4; i++) {
            cnt = Integer.parseInt(st.nextToken());
            if (i == 0) ch = '+';
            else if (i == 1) ch = '-';
            else if (i == 2) ch = '*';
            else ch = '/';

            while (cnt-- > 0) {
                cals[j++] = ch;
            }
        }

        visited = new boolean[N - 1];
        selected = new int[N - 1];
        recur(0);

        bw.write(String.valueOf(max + "\n" + min));
        bw.flush();
        bw.close();
        br.close();
    }
}