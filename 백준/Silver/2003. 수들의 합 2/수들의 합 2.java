import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        int m  = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[n + 1];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0, e = 0, total = nums[0], cnt = 0;
        while (e < n) {
            if (total < m) {
                e++;
                total += nums[e];
            } else if (total > m) {
                total -= nums[s];
                s++;
            } else {
                cnt++;
                total -= nums[s];
                s++;
                e++;
                total += nums[e];
            }
        }

        System.out.println(cnt);
    }
}