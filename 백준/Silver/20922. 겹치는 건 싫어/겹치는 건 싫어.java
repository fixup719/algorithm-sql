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

        int[] cnt = new int[100_001];
        int s = 0, e = 1, ans = 1;
        cnt[arr[s]]++;
        while (s <= e && e < N) {
            if (cnt[arr[e]] == K) {
                cnt[arr[s]]--;
                s++;
            } else {
                ans = Math.max(ans, e - s + 1);
                cnt[arr[e]]++;
                e++;
            }

        }

        System.out.println(ans);
    }
}

