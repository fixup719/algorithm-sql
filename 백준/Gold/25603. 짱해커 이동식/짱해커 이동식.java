import java.io.*;
import java.util.*;

public class Main {
    static int N, K, max;
    static int[] arr;

    static int binarySearch() {
        int s = 1, e = max, mid, cnt, ans = 1 << 30;
        while (s <= e) {
            mid = (s + e) / 2;

            cnt = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i] > mid) {
                    cnt++;
                    if (cnt >= K) break;
                } else {
                    cnt = 0;
                }
            }

            if (cnt >= K) {
                s = mid + 1;
            } else {
                ans = Math.min(ans, mid);
                e = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int ans = binarySearch();

        System.out.println(ans);
    }
}