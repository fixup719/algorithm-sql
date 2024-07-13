import java.io.*;
import java.util.*;


public class Main {
    static int d, n, m;
    static int[] arr;

    static int parametricSearch() {
        int s = 1, e = d, mid, dist, remain, ans = -1;
        while (s <= e) {
            mid = (s + e) / 2; // 점프하는 최소 거리

            remain = m;
            dist = 0;
            for (int i = 0; i < n + 1; i++) {
                dist += arr[i + 1] - arr[i];

                if (dist < mid) {
                    remain--;
                } else {
                    dist = 0;
                }
            }

            if (remain < 0) {
                // 최소거리를 크게 잡은 것이므로
                e = mid - 1;
            } else {
                // 최소거리 중 최댓값을 구해야 하므로
                ans = mid;
                s = mid + 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n + 2];
        arr[0] = 0;
        arr[n + 1] = d;

        int a;
        for (int i = 1; i < n + 1; i++) {
            a = Integer.parseInt(br.readLine());
            arr[i] = a;
        }
        Arrays.sort(arr);

        int answer = parametricSearch();

        System.out.println(answer);
    }
}
