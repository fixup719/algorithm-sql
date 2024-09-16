import java.io.*;
import java.util.*;

public class Main {
    static int n, s;
    static int[] x;

    // 11 17 19 21 22 23 28
    static int parametricSearch() {
        int start = 0, end = x[n - 1] - x[0], mid, cnt, pre, ans = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            cnt = 0;
            pre = 0;
            for (int i = 1; i < n; i++) {
                if (x[i] - x[pre] >= mid){
                    cnt++;
                    pre = i;
                }
            }
            cnt++;

            if (cnt >= s) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            s = Integer.parseInt(st.nextToken());
            x = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(x);
            int ret = parametricSearch();
            sb.append(ret).append("\n");
        }
        System.out.println(sb);
    }
}

