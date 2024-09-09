import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] prefix = new int[N + 1];
        int num;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            num = Integer.parseInt(st.nextToken());
            prefix[i] = prefix[i - 1] + num;
        }

        int sum;
        StringBuilder sb = new StringBuilder();
        int[] ans = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                sum = prefix[j] - prefix[i - 1];

                if (sum % 10 == 0) {
                    ans[i] = j - i + 1;
                    break;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (ans[i] > 0) sb.append(ans[i]).append(" ");
            else sb.append(-1).append(" ");
        }
        System.out.println(sb);
    }
}

