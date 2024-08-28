import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 팀 개수
        int K = Integer.parseInt(st.nextToken()); // 몇 명씩 배분할 지
        int M = Integer.parseInt(st.nextToken()); // 아이들 총 합
        int[] ans = new int[N + 1];

        int pointer = 1, dir = 0;
        while (M > 0) {
            if (M >= K) ans[pointer] += K;
            else ans[pointer] += M;
            M -= K;
            if (dir == 0) {
                // ->
                pointer++;
                if (pointer > N) {
                    pointer = N - 1;
                    dir = 1;
                }
            } else {
                // <-
                pointer--;
                if (pointer < 1) {
                    pointer = 2;
                    dir = 0;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb);
    }
}

