import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int N, X, s, e, cnt;
        int[] arr;
        for (int tc = 1; tc <= T; tc++) {
            sb.append("Case #").append(tc).append(": ");

            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            s = 0; e = N - 1; cnt = 0;
            while (s <= e) {
                if (arr[s] + arr[e] > X) {
                    cnt++;
                    e--;
                } else {
                    cnt++;
                    s++;
                    e--;
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }
}