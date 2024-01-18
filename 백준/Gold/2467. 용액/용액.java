import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] values = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0, e = n-1, sum = 0, minDiff = values[s] + values[e];
        int[] ans = {values[s], values[e]};
        while (s < e) {
            sum = values[s] + values[e];
            
            if (Math.abs(sum) < Math.abs(minDiff)) {
                minDiff = sum;
                ans[0] = values[s];
                ans[1] = values[e];
                if (sum == 0) break;
            } 

            if(sum < 0) s++;
            else e--;
        }

        System.out.println(ans[0] + " " + ans[1]);
    }
}