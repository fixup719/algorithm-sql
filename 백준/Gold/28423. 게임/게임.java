import java.io.*;
import java.util.*;

public class Main {
    static int L, R;
    static boolean[] visited;

    static int g(String N) {

        visited[Integer.parseInt(N)] = true;

        int sum1 = 0;
        // 각 자릿수 다 더하기
        for (int i = 0; i < N.length(); i++) {
            sum1 += N.charAt(i) - '0';
        }

        int sum2 = 1;
        // 각 자릿수 다 곱하기
        for (int i = 0; i < N.length(); i++) {
            sum2 *= N.charAt(i) - '0';
        }

        if (N.equals("" + sum1 + sum2)) return 1;
        else if (Integer.parseInt("" + sum1 + sum2) >= 100000) return -1;

        if (visited[Integer.parseInt("" + sum1 + sum2)]) return 0;

        return  g("" + sum1 + sum2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[100010];

        int answer = 0;
        for (int i = L; i <= R; i++) {
            answer += g(String.valueOf(i));
            Arrays.fill(visited, false);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}