import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        if (K <= 2) System.out.println(arr[N - 1]);
        else {
            // 3 5 5 5 6 7 8 9 15
            // 15 3 9 5 8 = 15  + 6 + 3 = 24
            // 15 3 9 5 8 5 = 15 6 3 = 24
            // 15 3 9 5 8 5 7 = 15 6 3 2 = 26

            int s = 0, e = N - 2, remain = K - 1;
            long ans = arr[N - 1];
            while (0 < remain) {
                if (remain != 1) ans += arr[e] - arr[s];
                s++;
                e--;
                remain -= 2;
            }
            System.out.println(ans);
        }

    }
}